import java.util.ArrayList;

public class Tree{
	
	private Node root;  

	
	public Tree() {
		root = null;
	
	}

	public void insert(int x) {

		
		if (root == null) {
			root = new Node(x);
		}
		else {
			if(root.search(x).keys.contains(x)) {
				
			}
			else {
			root.insert(x);
			}
		}
		
	}
	
	boolean search(int x) {
		if(root==null) {
			return false;
		}
		return root.search(x).keys.contains(x);
	}
	
	public int size(int x) {

		if (root==null) {
			return 0;
		}    // If the root is null, then tree doesn't exist -> return null
	        
		else {
			if(search(x)) {
				return root.search(x).subTreeCounter;
			}
			else {
				return 0;
			}
		}
		
		
		
	}	
	
 class Node {
		
		private ArrayList<Node> children;
		private ArrayList<Integer> keys;
		private int subTreeCounter;
      
        public Node(int x) {
			
			children = new ArrayList<>();
			keys= new ArrayList<>();
			keys.add(x);
			subTreeCounter++;
		
		}
        public void addKey(int x) {
        	keys.add(x);//we need to make sure it is a leaf
        	subTreeCounter++;
        	for(int i=0; i< keys.size()-1;i++) {
        		if(keys.get(i)>keys.get(keys.size()-1)) {
        			int temporal= keys.get(keys.size()-1);
        			int secondTemporal= keys.get(i);
        			keys.set(i, temporal);
        			keys.set(keys.size()-1, secondTemporal);
        		}
        	}
        	
    		
        }
        
        private boolean insert(int x)
        {
            if (this.keys.contains(x))                                               // If the node contains key,exit, done in main to
                return false;

            int i = 0;
            while (i < this.keys.size() && x > keys.get(i))        // position to insert
                i++;
            boolean splitChildren;

            if (i < this.children.size()) {                               // The node is not a leaf, so we can insert recursively
                splitChildren = this.children.get(i).insert(x);
            }
            else                                                        // If it is a leaf, add and check if we need to split
            {
                this.addKey(x);
                if (this.keys.size() == 3)
                {
                    this.split();                                    // If it was split return true to let the children know that
                    return true;                                        // that child was split.
                }
                return false;
            }

            if (splitChildren)
            {                                                           // Child was split and parent might be a 2-node or 3-node.
                Node tempChild = this.children.get(i);                  // Copy the split children
                this.addKey(tempChild.keys.get(0));                     // 
                this.children.set(i, tempChild.children.get(0));        // replace current with right children
                this.children.add(i+1, tempChild.children.get(1));      // replace right children with next index
                
                if (this.children.size() == 4)                           // if the node is full
                {
                    this.split();                                    // split the children to put them in different nodes
                    return true;
                    
                }
            }                                                           // The keys adjust
            return false;                                               
        }
        
       
        private void split()
        {
            Node left = new Node(this.keys.get(0));
            Node right = new Node(this.keys.get(2));
            if (this.children.size() == 4)                      // If the node is full, it balances itself
            {                                                   
                left.children.add(this.children.get(0));        
                left.children.add(this.children.get(1));
                right.children.add(this.children.get(2));
                right.children.add(this.children.get(3));
                
            }
            this.children.clear();
            this.children.add(left);                            // Set the new children right and left
            this.children.add(right);

            int tempKey = this.keys.get(1);                       // only keep the middle key
            this.keys.clear();   													//and get rid of the duplicates                                 
            this.keys.add(tempKey);      
            subTreeCounter=keys.size();
            for(int i=0; i<this.children.size();i++) {
            	subTreeCounter+=this.children.get(i).subTreeCounter;
            }
        }
        
        private Node search(int x)
        {
            if (this.children.size() == 0 || this.keys.contains(x))        // If the node is a leaf or has the key, return that node
                return this;
            else
            {
                int i = 0;
                while (i < this.keys.size() && x > keys.get(i))       // Else search for the appropriate branch by making use of i
                    i++;                                                                  
                return this.children.get(i).search(x);
            }
        }
        
        private int subTreeSize(int x)
        {
            if (this.children.size() == 0 || this.keys.contains(x)) {
            return keys.size()+ children.size();
            }
            else
            {
                int i = 0;
                while (i < this.keys.size() && x > keys.get(i))       // Else search for the appropriate branch by making use of i
                    i++;                                                                 
                return this.children.get(i).subTreeSize(x);
            }
        }
        
     
       
 }     
}

