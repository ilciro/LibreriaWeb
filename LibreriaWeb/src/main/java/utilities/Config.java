package utilities;



public class Config {
	private String host;
	private String user;
	private String pwd;
	private String database;
	private String port;
	private String driver;
	private String url;
	
	public Config()
	{
		this.host="localhost";
		this.user="root";
		this.pwd="root";
		this.database="ispw";
		this.port="3306";
		this.driver="com.mysql.cj.jdbc.Driver";
		this.url="jdbc:mysql://localhost/sys?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	}
	
	

	public String getHost()
	{
		return this.host;
	}
	
	public String getUser()
	{
		return this.user;
	}
	
	public String getPwd()
	{
		return this.pwd;
	}
	
	public String getDB()
	{
		return this.database;
	}
	
	public String getPort()
	{
		return this.port;
	}
	
	public String getDriver()
	{
		return this.driver;
	}
	
	public String getUrl()
	{
		return this.url;
	}
	public void setHost(String host)
	{
		this.host=host;
	}
	public void setDB(String dB)
	{
		this.database=dB;
	}
	
	

}
