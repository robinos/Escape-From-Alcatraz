/**
 * The Container class is not currently used.  It is meant to represent
 * chests, trunks, cells, or anything that can hold a GameObject that can
 * exist in the game world.
 * This functionality could instead be represented by Backpack, Room, or Item.
 * Since ObjectWatcher is meant to know all GameObject locations, the ArrayList
 * held by Container does not need to exist.  It is commented out.  
 * This class is not removed yet, just in case it might have some future use.
 * 
 * @author  : Group 8
 * @version : 2012 02 23, Version 1.4
 */

package Model;
//import java.util.Iterator;
//import java.util.ArrayList;

public class NotInUseContainer extends GameObject implements Cloneable
{
	//Container is a subclass of GameObject that has a serialVersionUID of 1L
	//Subclasses of Container should have a serialVersionUID of 14xL where x
	//is a value 1-9.	
    private static final long serialVersionUID = 14L;	
	
    // instance variables
    //ArrayList<GameObject> objectList;

    /**
     * Main Constructor for objects of class Container
     * 
     *  GameObject parameters (See GameObject)
     */
    public NotInUseContainer(int id, int location, int xLoc, int yLoc, int width, int height, String picture, String name) //ArrayList<GameObject> objectList)
    {
        super(id,location,xLoc,yLoc,width,height,picture,name);
        //this.objectList = objectList;
    }
    
    /**
     * No argument Constructor for objects of class Container.
     * This is not meant to be used and exists only for serialization.
     */
    public NotInUseContainer()
    {
        super();
        //this.objectList = new ArrayList<GameObject>();
    }    
    
    
    /**
     * getObjectList returns the list of GameObjects contained in the Container
     * 
     * @return : the ArrayList of items
     *
    public ArrayList<GameObject> getObjectList()
    {
        return objectList;
    }*/    
    
    
    /**
     * createObject creates a new GameObject in the container.
     * 
     * @return : true if the item was successfully added, otherwise false
     *
    public boolean createObject(GameObject object)
    {    
        if(object != null) {
            objectList.add(object);
            object.moveObject(this.getObjectLocation().getLocation());
            return true;
        }
        
        return false;
    }*/    
    
    
    /**
     * destroyItem destroys a GameObject in a container.
     * 
     * @param object : a GameObject
     * @return       : true if the item was successfully removed, otherwise false
     *
    public boolean destroyObject(GameObject object)
    {    
        boolean objectExists = false;
        Iterator<GameObject> it = objectList.iterator();        
        
        //Make sure the object exists in the container
        while( it.hasNext() ) {    
           GameObject temp = it.next();
           if(object.getObjectID() == temp.getObjectID()) {
              objectExists = true;
              break;
           }
           //End if matching object
        }
        //End for loop
            
        //If the item exists in the container
        if(objectExists) {
            objectList.remove(object);
            object.moveObject(9999);  //Objects moves to 9999 are removed from the main HashMap           
            return true;
        }
        //End if exists
        
        return false;
    }*/ 
    
    
    /**
     *  This is the clone method for Container.  It overrides the clone()
     *  method of GameObject, and is meant to be overridden where needed by
     *  subclass methods. 
     */
    @Override 
    public NotInUseContainer clone()
    {
       //Shallow copy of container
       NotInUseContainer copy = (NotInUseContainer)super.clone();
       
       //Shallow copy of groupList
      //copy.objectList = (ArrayList<GameObject>)objectList.clone();
       
       //copy of all elements of groupList
       //for(int i=0;i<objectList.size();i++)
       //{
       //    copy.objectList.set(i,objectList.get(i).clone());
       //}
       
       return copy;
    }     
    
    
    /**
     *  A toString method for Container that prints out the id and location.
     *  
     *  @return : A String with information on the id and location.
     */
    @Override     
    public String toString()
    { 
    	return super.toString();
    }
    
    
    /**
     *  A basic equals method for Container that compares id and location values.
     *  
     *  @param obj : the object for comparison 
     *  @return    : a boolean value true or false 
     */
    @Override
    public boolean equals(Object obj)
    { 
    	if(this == obj) {
    		return true;  //The same object
    	}
    	if(!(obj instanceof NotInUseContainer)) {
    		return false;  //Not of the same type
    	}
    	
    	NotInUseContainer other = (NotInUseContainer)obj;
    	
    	//Compare the id and location of the Item to the id and location of
    	//the other Item and return true or false
    	return( ( other.getObjectID() == this.getObjectID() ) &&
    			( other.getObjectLocation() == this.getObjectLocation() ) );
    }    
    
    
    /**
     *  A basic hashCode method for Container, for the id and location fields
     *  that are compared in the equals method.
     *  
     *  @return : an integer value
     */
    @Override
    public int hashCode()
    {        	
    	return(super.hashCode());
    }
 
}