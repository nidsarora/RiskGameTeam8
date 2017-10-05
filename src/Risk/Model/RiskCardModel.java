/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Risk.Model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


public class RiskCardModel {

   
   public String value;
   public int territory;

    public RiskCardModel(int t, String v)
    {
    	this.value = v;
    	this.territory = t;   	
    }
    
    public RiskCardModel(int v, int t){
        this.territory = t;
        switch(v)
        {
        case 0:
        	this.value = "INFANTRY";
        	break;
        case 1:
        	this.value = "CAVALRY";
        	break;
        case 2:
        	this.value ="ARTILLERY";
        	break;
        default:
        	this.value = "WILD";
        	break;
        }
        	
        
    }
    
    @Override
    public String toString()
    {
    	return "Territory \n" + String.valueOf(territory) + "\n\n Value \n" + String.valueOf(value) + "-" +String.valueOf(territory) + "-" +  value;   	
    }

}
