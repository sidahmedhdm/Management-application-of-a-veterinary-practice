import java.sql.*;

public class Sys {
	//connection
	 
	private static Connection c=null;
	//constricteure
	public Sys(){}
	public Sys(Connection c){
		this.c=c;
	}
	
	boolean isNumber(char ch){
		if(ch!='0'&&ch!='1'&&ch!='2'&&ch!='3'&&ch!='4'&&ch!='5'&&ch!='6'&&ch!='7'&&ch!='8'&&ch!='9')
		return false;
		else return true;
	}
 public void ajout(String s,String t){
	 Statement st=null;
	 try{
		 st=c.createStatement();
		 st.execute("insert into "+t+" values("+s+")");
		 st.close();
	 }catch(SQLException sq){
		 sq.printStackTrace();
	 }
 }
 
 public void sup(int i,String t){
	 String cha = t.charAt(0)+"";
	 if (t=="Visite") cha = "Vi";
	
	 this.exe("delete from "+t+" where id"+cha+"="+i);
	  }
 
 public int getSeq(String t){
	 int o=0; String cha="";boolean c=false,b=false;
	 switch (t){
	 case "Client":c=true;o=new Client().getI();cha="C";break;
	 case "Animal":o=new Animal().getI();cha="A";break;
	 case "RDV":o=new RDV().getI();cha="R";break;
	 case "Medicament":o=new Medicament().getI();cha="M";break;
	 case "Visite":o=new Visite().getI();cha="Vi";break;
	 case "Vente":o=new Medicament().getIv();cha="V";break;
	 }
	 o++;
	 
	  	 
	
		 ResultSet r=this.res("select id"+cha+" from "+t);
		 try{
			 while(r.next()&&!b){
				 
				 if(r.getInt(1)==o){o++;
}
				 else {b=true; 
}}
		 }catch(SQLException e){ e.printStackTrace();
		 }
	 return o;
 }
 
 public ResultSet res(String req){
	 Statement st=null;
	 ResultSet r=null;
	 try{
		 st=c.createStatement();
	     r=st.executeQuery(req);
	 }catch(SQLException sq){
		 sq.printStackTrace();
	 }
	 return r;
 }
 public void exe(String req){
	Statement st=null;
	 try{
		 st=c.createStatement();
		 st.execute(req);
		  st.close();
	 }catch(SQLException sq){
		 sq.printStackTrace();
	 }
 }
 
 public void videzT(String t){
	 this.exe("delete from "+t);
 }
 public void videzDB(){
	 this.videzT("Vente");
	 this.videzT("Visite");
	 this.videzT("RDV");
	 this.videzT("Animal");
	 this.videzT("Client");
 }
 public ResultSet Rech(int id,String t){
	 String cha = t.charAt(0)+"";
	 if(t=="Visite") cha="Vi";
	return this.res("select * from "+t+" where id"+cha+" ="+id);
 }
 public Animal extA(int id){
	 Animal A = null;
	 ResultSet r=Rech(id,"Animal");
	 try{
		 if(r.next()){
			 A = new Animal();
			 A.setIda(r.getInt(1));
		     A.setIdc(r.getInt(2));
		     A.setNom(r.getString(3));
		     A.setRace(r.getString(4));}
	 }catch(SQLException e){
		 e.printStackTrace();
	 } return A;
 }
 public RDV extR(int id){
	 RDV R = null;
	 ResultSet r=Rech(id,"RDV");
	 try{
		 if(r.next()){
			 R = new RDV();
			 R.setIdR(r.getInt(1));
		     R.setIdC(r.getInt(2));
		     R.setIdA(r.getInt(3));
		     R.setD(r.getString(4));}
	 }catch(SQLException e){
		 e.printStackTrace();
	 } return R;}
 public Visite extV(int id){
	 Visite A = null;
	 ResultSet r=Rech(id,"Visite");
	 try{
		 if(r.next()){
			 A = new Visite();
			 A.setIdvi(r.getInt(1));
		     A.setIdc(r.getInt(2));
		     A.setIdr(r.getInt(3));
		     A.setIda(r.getInt(4));
		     }
	 }catch(SQLException e){
		 e.printStackTrace();
	 } return A;
 }
 public Client extC(int id){
	 Client A = null;
	 ResultSet r=Rech(id,"Client");
	 try{
		 if(r.next()){
			 A = new Client();
			 A.setIdc(r.getInt(1));
		     A.setNom(r.getString(2));
		     A.setPrenom(r.getString(3));
		     A.setNum_tel(r.getString(4));
		     }
	 }catch(SQLException e){
		 e.printStackTrace();
	 } return A;
 }
 public Medicament extM(int id){
	 Medicament A = null;
	 ResultSet r=Rech(id,"Medicament");
	 try{
		 if(r.next()){
			 A = new Medicament();
			 A.setIdm(r.getInt(1));
		     A.setNomm(r.getString(2));
		     A.setDate_exp(r.getString(3));
		     A.setPrix(r.getFloat(4));
		     A.setContité(r.getInt(5));
		     
		     }
	 }catch(SQLException e){
		 e.printStackTrace();
	 } return A;
 }
 public void actualiser(){
	 new Animal().setSeq();
	 new Client().setSeq();
	 new RDV().setseq();
	 new Visite().setseq();
	 new Medicament().setSeq();
 }
 
 public void createdb(){

	 exe("create table Client(idC int not null primary key,nomC varchar(30)not null, prenomC varchar(30) not null ,num_tlfn char(14))");
	 exe("create table Animal(idA int not null primary key,idC int not null ,nomA varchar(30),race varchar(30),sexe varchar(15),foreign key (idC) references Client(idC) )");
	 exe("create table RDV(idR int not null primary key,idC int not null,idA int not null,d date,foreign key(idC)references Client(idC),foreign key(idA)references Animal(idA))");
	 exe("create table Medicament(idM int not null primary key,nomM varchar(30)not null,date_exp date,prix float,contité int)");
	 exe("create table Vente(idV int not null primary key,idC int not null,idM int not null,nbr int,prix float,foreign key(idC)references Client(idC),foreign key(idM)references Medicament(idM))");
	 exe("create table Visite(idVi int not null primary key,idC int not null,idR int not null,idA int not null,prix float,foreign key(idR)references RDV(idR),foreign key(idC)references Client(idC),foreign key(idA)references Animal(idA))");
	 exe("create table caisse(ret_ven float not null primary key,ret_vis float)");
	 boolean b=false;
	 
	 ResultSet r=res("select * from RDV");
	 try{
		 if(r.next()) b=true;
	 }catch(SQLException e){
		 e.printStackTrace();
	 }
	 
	 
	 if(!b){
	 actualiser();
	 Client cl=new Client("Nom du client", "Prénom du client", "000000000");
	 actualiser();
	 Animal an=new Animal("Nom animal", "race animal", "male", cl);
	 actualiser();
	 RDV rd=new RDV(an, "1111-11-11");
	 actualiser();
	 Medicament m=new Medicament("Nom de medicament", "1111-11-11", 150, 0);
	 cl.ajout();an.ajout();rd.ajout();m.ajout();}
	 
 }
 

}
