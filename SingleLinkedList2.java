
public class SingleLinkedList2 {
private Node head;
	
	public SingleLinkedList2()
	{
		head = null;
	}
	
	public void addToEnd(Object dataToAdd)
	{
		Node newNode = new Node(dataToAdd);
		
		if(head == null)
		{
			head = newNode;
		}
		else
		{
			Node temp = head;
			
			while(temp.getLink() != null)
			{
			   temp = temp.getLink();
			}
			
			temp.setLink(newNode);
		}
	}
	
	public String display()
	{
	    String output = "";
	    
	    Node temp = head;
		
		while(temp != null)
		{
		   output += temp.getData() + " ";
		   temp = temp.getLink();
		}
	    
	    return output;
	}
	
	public void remove(Object dataToRemove)
	{
		if(head == null)
		{
			System.err.println("The Linked List is empty");
		}
		else
		{
				while((int)head.getData() == (int)dataToRemove)
				{
					head = head.getLink();
				}
			
			Node temp = head;
			Node prev = null;
			while(temp != null)
			{
				
				if((int)temp.getData() == (int) dataToRemove)
				{
					prev.setLink(temp.getLink());
					temp = temp.getLink();
				}
				else
				{
				    prev = temp;
				    temp = temp.getLink();
				}
			}
		}
	}
	
	public int size()
	{
		if(head == null)
		{
			System.err.println("The Linked List is empty");
			return 0;
		}
		else
		{
			int counter = 0;
			
			Node temp = head;
			while(temp != null)
			{
				counter++;
				temp = temp.getLink();
			}
			
			return counter;
		}
	}
	
	public boolean search(Object dataToSearch)
	{
		if(head == null)
		{
			return false;
		}
		else
		{
		    Node temp = head;
		    
		    boolean retVal = false;
		    while(temp != null)
		    {
		    	if((int)temp.getData() == (int) dataToSearch)
		    	{
		    		retVal = true;
		    		break;
		    	}
		    	
		    	temp = temp.getLink();
		    }
		    
		    return retVal;
		}
	}
	public int book(int value) {
		int bookCount = 0;
		Node temp = head;
		 while(temp != null) {
			 if((int)temp.getData() == value) {
					bookCount++;
				}
			 temp = temp.getLink();
		 }
		return bookCount;
	}
	
}
