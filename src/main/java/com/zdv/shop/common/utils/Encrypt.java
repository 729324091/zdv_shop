package com.zdv.shop.common.utils;


import java.security.MessageDigest;


/**
 * @author shuxj
 * @version 1.0
 */
public class Encrypt
{
    private static char[] MD5_TABLE = new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

    public Encrypt()
    {
    }


    public static String encrypt(String datas, String key)
    {
        String encryptString = null;

        DESedeWithSHA enc = DESedeWithSHA.getInstance();
        byte[] plainText = enc.encrypt(datas, key);
        encryptString = Base64.encode(plainText);

        return encryptString;
    }


    public static String decrypt(String plainText, String key)
    {
        byte[] decryptMSG = null;
        decryptMSG = Base64.decode(plainText);
        DESedeWithSHA enc = DESedeWithSHA.getInstance();
        return enc.decrypt(decryptMSG, key);
    }

    public static String SHA(String datas)
    {
        byte[] digest = DESedeWithSHA.getInstance().sha(datas);
        String msg = Base64.encode(digest);
        return msg;
    }


    public static String md5(String x)
    {
        try
        {
        	if(x==null||x.equals(""))
        		return null;
            return md5(x.getBytes("UTF8")).toUpperCase();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
     
    
    public static String md5(byte[] bytes)
    {       
        try
        {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(bytes);
            byte s[] = m.digest();
            StringBuffer buf = new StringBuffer(s.length*2);
            for(int i=0;i<s.length;i++){
                buf.append(MD5_TABLE[(s[i]>>4)&0x0f]);
                buf.append(MD5_TABLE[s[i]&0x0f]);
            }
            return buf.toString();

        }
        catch (Exception e)
        {
            // e.toString();
            e.printStackTrace();
            return null;
        }        
    }
	 public static String getIdIsMd5(long id){
			if(id>0){
				String strid=Encrypt.md5(String.valueOf(id));
				String sid = id+"";
				String rid = "";
				char firstchar = strid.charAt(0);
				int n = firstchar%2+1;
				for(int i=0;i<sid.length();i++){
					rid += strid.substring(n*i,n*i+n)+sid.charAt(i);
				}
				rid +=strid.substring(n*sid.length(),strid.length());
				return rid.substring(0, 31)+sid.length();
			}else{
				return "0";			
			}
		}
	 public static Long getIdUnMd5(String id){
		 Long rid=0L;
		 try{
		 char firstchar = id.charAt(0);
		 char lastchar = id.charAt(id.length()-1);
		 int n = firstchar%2+1;
		 
		 String strid="";
		 for(int i=0;i<Integer.parseInt(lastchar+"");i++){
			 strid += id.charAt((n+1)*(i+1)-1);
		 }
		 if(!(strid==null||strid.equalsIgnoreCase(""))){
			 rid = Long.parseLong(strid);
		 }
		 }catch(Exception e){
			 e.printStackTrace();
			 return 0L;
		 }
		 return rid;
	 }
    public static void main(String[] args)
    {

        System.out.println(md5("123456"));//
    }
}
