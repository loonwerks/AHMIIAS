package com.soartech.simple;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class RlRuleGenerator extends Application
{
    static final String NAME = "set-throttle*rl";
    static final String FIRST = "(state <s> ^operator <o> +)\r\n" + 
                                " (<o> ^error-info <ei>)";
    static final String LAST = " -->\r\n" + 
                               " (<s> ^operator <o> = 0.0)";
    static final String ALWAYS = "(<o> ^name [warn do-not-warn])\r\n";
    static final String ALWAYS_NAME = "operator";
    static final String[][] CHOICES_ARRAY = {
            {
                "(<ei> ^error [gps-imu gps-lidar lidar-imu])"
            },
            {
                "(<ei> ^warn-condition [low high])"
            },
            {
                "(<s> ^flight-mode [vertical horizontal])"
            },
            {
                "(<s> ^name [takeoff flying])",
                //"(<g> ^climb-rate-to-10 [low -50 -40 -30 -20 -10 0 10 20 30 40 50 high])"
            }
    };

    static final String[][] CHOICE_NAMES_ARRAY = {
            {
                "error-type"
            },
            {
                "warn-condition"
            },
            {
                "flight-mode"
            },
            {
                "mission-phase"
            }
    };

    public static void main(String[] args)
    {
        Platform.setImplicitExit(true);
        Application.launch(args);;
    }
    
    @Override
    public void start(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save RL Rules File");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("SOAR Files", ".soar"));
        fileChooser.setInitialFileName("initial-rl-rule-set.soar");
        File saveFile = fileChooser.showSaveDialog(stage);
        if (saveFile != null) {
            System.out.println("Generating productions");
            List<String> generatedProductions = generateProductions();
            System.out.println();
            System.out.print("Saving to file " + saveFile);
            PrintWriter out;
            try
            {
                out = new PrintWriter(saveFile);
                for (String p : generatedProductions) {
                    System.out.print(".");
                    out.println(p);
                }
                out.close();
                System.out.println("Done");
            }
            catch (FileNotFoundException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        stage.close();
        Platform.exit();

    }
    
    private List<String> generateProductions() {
        List<List<String>> choicesList = new Vector<List<String>>();
        List<List<String>> choiceNamesList = new Vector<List<String>>();
        for (String[] choices : CHOICES_ARRAY) {
            List<String> temp = new Vector<String>();
            for (String c : choices) {
                temp.add(c);
            }
            choicesList.add(temp);
        }
        for (String[] choices : CHOICE_NAMES_ARRAY) {
            List<String> temp = new Vector<String>();
            for (String c : choices) {
                temp.add(c);
            }
            choiceNamesList.add(temp);
        }
        List<String> result = new Vector<String>();
        generateProductions(NAME, FIRST, LAST, ALWAYS, choicesList, choiceNamesList, 0, result);
        return result;
    }
    
    private void generateProductions(String name, 
            String first, 
            String last,
            String always, 
            List<List<String>> choicesList, 
            List<List<String>> choiceNamesList, 
            int choicesIndex, 
            List<String> result) {
        if (choicesList == null || choicesIndex >= choicesList.size()) {
            List<String> choices = extractChoices(always);
            if (choices == null) {
                String prod = "sp {" + name + "*" + ALWAYS_NAME + "\r\n" + first + "\r\n" + always + "\r\n" + last + "}\r\n";
                System.out.print(".");
                result.add(prod);
            } else {
                for (String choice : choices) {
                    String prod = "sp {" + name + "*" + ALWAYS_NAME + "-" + choice + "\r\n" + first + "\r\n" + replaceWithChoice(always, choice) + "\r\n" + last + "}\r\n";
                    System.out.print(".");
                    result.add(prod);
                }
            }
            return;
        }
        
        String newName = name + "*x";
        generateProductions(newName, first, last, always, choicesList, choiceNamesList, choicesIndex + 1, result);
        for (int i = 0; i < choicesList.get(choicesIndex).size(); i++) {
            String choice = choicesList.get(choicesIndex).get(i);
            List<String> valueChoices = extractChoices(choice);
            if (valueChoices == null) {
                newName = name + "*" + choiceNamesList.get(choicesIndex);
                String newFirst = first + "\r\n" + choice;
                generateProductions(newName, newFirst, last, always, choicesList, choiceNamesList, choicesIndex + 1, result);
            } else {
                for (String vc : valueChoices) {
                    newName = name + "*" + choiceNamesList.get(choicesIndex).get(i) + "-" + vc;
                    String newFirst = first + "\r\n" + replaceWithChoice(choice, vc);
                    generateProductions(newName, newFirst, last, always, choicesList, choiceNamesList, choicesIndex + 1, result);
                }
            }
        }
    }
    
//    private List<String> generateProductions(String name, String first, String last, List<String> always, List<List<String>> choicesList, List<List<String>> choiceNamesList, List<String> result) {
//        System.out.println(name + " choices " + choicesList.size());
//        if (choicesList == null || choicesList.isEmpty()) {
//            for (String alwaysChoice : always) {
//                String prod = "sp {" + name + "*" + ALWAYS_NAME + "-" + alwaysChoice + "\r\n" + first + "\r\n" + alwaysChoice + "\r\n" + last + "}\r\n";
//                System.out.print(".");
//                result.add(prod);
//            }
//            return result;
//        }
//        
//        List<List<String>> cloneChoicesList = new Vector<List<String>>(choicesList);
//        List<List<String>> cloneChoiceNamesList = new Vector<List<String>>(choiceNamesList);
//        List<String> choices = cloneChoicesList.remove(0);
//        List<String> choiceNames = cloneChoiceNamesList.remove(0);
//        String newName = name + "*x";
//        result = generateProductions(newName, first, last, always, cloneChoicesList, cloneChoiceNamesList, result);
//        for (String choice : choices) {
//            List<String> expandedChoice = expandToList(choice);
//            for (String ec : expandedChoice) {
//                newName = name + "*" + choiceNames.get(choices.indexOf(choice)) + "-" + ec;
//                String newFirst = first + "\r\n" + ec;
//                result = generateProductions(newName, newFirst, last, always, cloneChoicesList, cloneChoiceNamesList, result);
//            }
//        }
//        return result;
//    }
    
    private List<String> extractChoices(String s) {
        Vector<String> result = new Vector<String>();
        int start = s.indexOf('[');
        if (start < 0) {
            return null;
        }
        int end = s.indexOf(']');
        if (end <= start) {
            return null;
        }
        String[] items = s.substring(start+1, end).split("\\s");
        for (String i : items) {
            result.add(i);
        }
        return result;
    }
    
    private String replaceWithChoice(String s, String choice) {
        int start = s.indexOf('[');
        if (start < 0) {
            return s;
        }
        int end = s.indexOf(']');
        if (end <= start) {
            return s;
        }
        return s.substring(0, start) + choice + s.substring(end+1);
    }
    
//    private List<String> expandToList(String s) {
//        Vector<String> result = new Vector<String>();
//        int start = s.indexOf('[');
//        if (start < 0) {
//            result.add(s);
//        } else {
//            int end = s.indexOf(']');
//            if (end <= start) {
//                result.add(s);
//            } else {
//                String stringStart = s.substring(0,start);
//                String stringEnd = s.substring(end+1);
//                String[] items = s.substring(start+1, end).split("\\s");
//                for (String i : items) {
//                    result.add(stringStart + i + stringEnd);
//                }
//            }
//        }
//        return result;
//    }

}


