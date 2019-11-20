import java.sql.*;
public class Animal extends Sys{
	private int ida;
	private String sexe;
	private String nom;
	private String race;
    private int idc;
    private  static int seq=7851;
    private final static int i=7850;
    public int getI(){return i;}
	
	public int getIda() {
		return ida;
	}
	public void setIda(int ida) {
		this.ida = ida;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public Animal(){}
	public Animal(String nom,String race,String sexe,Client cl){
		this.nom=nom;this.race=race;idc=cl.getIdc();
		ida=seq;this.sexe=sexe;
	}
	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public static int getSeq() {
		return seq;
	}
	public int getIdc() {
		return idc;
	}
	public void setIdc(int idc) {
		this.idc = idc;
	}
	public void setSeq(){
		seq= super.getSeq("Animal");
	}
		
	public void ajout(){
		String s=ida+","+idc+",'"+nom+"','"+race+"','"+sexe+"'";
		super.ajout(s,"Animal");
	}
	public void sup(){
		this.sup(ida, "Animal");
	}
}
