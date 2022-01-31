import java.util.ArrayList;
public class Variable{
    String name;
    ArrayList<String> values;
    // TYPE constants
    public static final int INVALID=-1,S_CONST = 0, INT = 1, FLOAT = 2;
    public int varType=-1;
    public String initialValue = "";
    public Variable(String name){
        this.name = name;
        this.values = new ArrayList<String>();
    }

    public void addValue(String val){
        if(!values.contains(val)){
            values.add(val);
        }
    }

    public void generateType(){
        int type=Variable.INVALID;
        for(String value : values){
            if(value.equals("nil")){
                continue;
            }
            int vType = getValueType(value);
            if(type == Variable.INVALID){
                type = vType;
            }else if(type != vType){
                this.varType = Variable.S_CONST;
                return;
            }
        }
        //this.varType = type==-1?S_CONST:type;
        this.varType = type;
    }

    public int getValueType(String val){
        try{
            Integer.parseInt(val);
            return Variable.INT;
        }catch(Exception e){
            try{
                Double.parseDouble(val);
                return Variable.FLOAT;
            }catch(Exception e1){
                return Variable.S_CONST;
            }

        }
    }


}
