import java.sql.*;

public class Test {
  static Connection c=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
String url="jdbc:derby:database/DB;create=true";
try{
	c=DriverManager.getConnection(url);
}catch(Exception e){
	new Jf("Erreur");
	e.printStackTrace();
}

Sys s=new Sys(c);
s.createdb();
s.actualiser();


Fenetre f=new Fenetre();





	}
}
