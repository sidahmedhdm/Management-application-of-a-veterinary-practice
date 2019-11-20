import java.sql.*;
public class Visite extends Sys {
	
	private int idvi;
	private int idc;
	private int idr;
	private int ida;
	private final float prix=800;
	private static int seq=4001;
	 private final static int i=4000;
	    public int getI(){return i;}
	
	public int getIdvi() {
		return idvi;
	}
	public void setIdvi(int idvi) {
		this.idvi = idvi;
	}
	public int getIdc() {
		return idc;
	}
	public void setIdc(int idc) {
		this.idc = idc;
	}
	public int getIdr() {
		return idr;
	}
	public void setIdr(int idr) {
		this.idr = idr;
	}
	public int getIda() {
		return ida;
	}
	public void setIda(int ida) {
		this.ida = ida;
	}
	public float getPrix() {
		return prix;
	}
	public Visite(){
		
	}
	public Visite(RDV r){
		idvi=seq; idc=r.getIdC();idr=r.getIdR();ida=r.getIdA();
	}
	public void ajout (){
		String s=idvi+","+idc+","+idr+","+ida+","+prix;
		super.ajout(s,"Visite");
	}
	public void setseq(){
		int o=super.getSeq("Visite");
		if(o!=0)
		seq=o;
	}
	public int getSeq(){return seq;}
	public void sup(){
		this.sup(idvi, "Visite");
	}
	

}
