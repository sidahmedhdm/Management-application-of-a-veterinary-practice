import java.sql.*;
public class RDV extends Sys{
	private int idR;
	private int idC;
	private int idA;
	private String D;
	private static int seq=6801;
	 private final static int i=6800;
	    public int getI(){return i;}
	
	public RDV(){}
	public RDV(Animal A,String D){
    this.idC=A.getIdc();this.idA=A.getIda();this.D=D;idR=seq;
	} 
	public void ajout(){
		
		String s=idR+","+idC+","+idA+",'"+D+"'";
		super.ajout(s,"RDV");
	}
	public int getIdR() {
		return idR;
	}
	public void setIdR(int idR) {
		this.idR = idR;
	}
	public int getIdC() {
		return idC;
	}
	public void setIdC(int idC) {
		this.idC = idC;
	}
	public int getIdA() {
		return idA;
	}
	public void setIdA(int idA) {
		this.idA = idA;
	}
	public String getD() {
		return D;
	}
	public void setD(String d) {
		D = d;
	}
	
	public void setseq(){
		int o =super.getSeq("RDV");
		if(o!=0)
		seq=o;
	}
	public int getSeq(){
		return seq;
	}
	public void sup(){
		this.sup(idR, "RDV");
	}
	
}
