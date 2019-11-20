import java.sql.*;
public class Medicament extends Sys {

	private int idm;
	private String nomm;
	private String date_exp;
	private float prix;
	private int contit�;
	private static int seq=107;
	private static int seqv=11;
	 private final static int i=106; 
	 private final static int iv=10;
	 
	 
	    public int getI(){return i;}
	    public int getIv(){return iv;}

	

	public Medicament (){}
	public Medicament (String nomm,String date_exp,float prix,int contit�){
		idm=seq;
		this.nomm=nomm;this.date_exp=date_exp;this.prix=prix;this.contit�=contit�;
	}
	public int getIdm() {
		return idm;
	}
	public void setIdm(int idm) {
		this.idm = idm;
	}
	public String getNomm() {
		return nomm;
	}
	public void setNomm(String nomm) {
		this.nomm = nomm;
	}
	public String getDate_exp() {
		return date_exp;
	}
	public void setDate_exp(String date_exp) {
		this.date_exp = date_exp;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public int getContit�() {
		return contit�;
	}
	public void setContit�(int contit�) {
		this.contit� = contit�;
	}

	public int getSeq(){return seq;}
	public int getSeqv(){return seqv;}
	public void setSeq(){
		seq= super.getSeq("Medicament");
		
		seqv= super.getSeq("Vente");
	}	
	
	public void ajout(){
		String s=idm+",'"+nomm+"','"+date_exp+"',"+prix+","+contit�;
		super.ajout(s,"Medicament");
		
	}
	public void sup(){
		this.sup(idm, "Medicament");
	}
	
public boolean vente(Client c,int nbr){
	int idc=c.getIdc(), idv=seqv; 
	if(nbr>contit�) return false;
	else {this.ajout(idv+","+idc+","+idm+","+nbr+","+prix*nbr, "Vente");
	contit�-=nbr;
	this.exe("update Medicament set contit�="+contit�+" where idM="+idm);
	return true;
	}
}
}
