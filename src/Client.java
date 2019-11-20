import java.sql.*;
public class Client extends Sys {
	private int idc;
	private String nom;
	private String prenom;
	private String num_tel;
	private static int seqC=149;
	 private final static int i=148;
	    public int getI(){return i;}
	
	public Client(){}
	public Client(String nom,String prenom,String num_tel){
		this.nom=nom;this.prenom=prenom;this.num_tel=num_tel;
	idc=seqC;
	}
	
	public int getSeq(){
		return seqC;
	}
	public void setSeq(){
		seqC= super.getSeq("Client");
		
	}
	
	public int getIdc() {
		return idc;
	}

	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNum_tel() {
		return num_tel;
	}
	public void setNum_tel(String num_tel) {
		this.num_tel = num_tel;
	}
	public void setIdc(int idc) {
		this.idc = idc;
	}
	public void ajout(){
		String s=idc+",'"+nom+"','"+prenom+"','"+num_tel+"'";
		super.ajout(s,"Client");
	}
	public void sup(){
		this.sup(idc, "Client");
	}

}
