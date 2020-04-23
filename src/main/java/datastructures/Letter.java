package datastructures;

/**
 * Letter tree component.
 */
public class Letter {
    private Letter[] childs;    
    private int code;

    /**
     * Constructor.
     */
    public Letter() {
        this.code = -1;
    }

    /**
     * getCode
     * 
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * setCode
     * 
     * @param code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * setChilds
     * 
     * @param childs
     */
    public void setChilds(Letter[] childs) {
        this.childs = childs;
    }    
    
    /**
     * Method to check if child is initialized in index.
     * 
     * @param index     Index
     * @return          True if already initialized
     */
    public boolean isChildAlreadyInitialized(int index) {
        if (this.childs[index] != null) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Method to get child in index.
     * 
     * @param index     Index
     * @return          Child in index
     */
    public Letter getChildInIndex(int index) {
        return childs[index];
    }
    
    /**
     * Method
     * 
     * @param newCode
     */
    public void initialize(int newCode) {
        this.setChilds(new Letter[256]);        
        this.setCode(newCode);
    }

    /**
     * Method to initialize child in index.
     * 
     * @param index     Index
     * @param newCode   Code for the child
     */
    public void initializeChild(int index, int newCode) {
        this.childs[index] = new Letter();
        this.childs[index].initialize(newCode);
    }
}
