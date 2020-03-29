package datastructures;

public class Letter {
    private Letter[] childs;    
    private int code;

    public Letter() {
        this.code = -1;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setChilds(Letter[] childs) {
        this.childs = childs;
    }    
    
    public boolean isChildAlreadyInitialized(int index) {
        if (this.childs[index] != null) {
            return true;
        } else {
            return false;
        }
    }
    
    public Letter getChildInIndex(int index) {
        return childs[index];
    }
    
    public void initialize(int newCode) {
        this.setChilds(new Letter[256]);        
        this.setCode(newCode);
    }

    public void initializeChild(int index, int newCode) {
        this.childs[index] = new Letter();
        this.childs[index].initialize(newCode);
    }
    
    
}
