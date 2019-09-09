package com.zdv.shop.common.utils;

/**
 * @author shuxj
 * @version 1.0
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DESedeWithSHA
{
    /**
     */
    private static String Algorithm = "DESede"; //���� �����㷨,���� DES,DESede,Blowfish

    private DESedeWithSHA(String algorithm)
    {
        if (algorithm.equals("DES") || algorithm.equals("DESede") ||
            algorithm.equals("Blowfish"))
        {
            Algorithm = algorithm;
        }
    }

    private DESedeWithSHA()
    {
        Algorithm = "DESede";
    }

    /**
     * ȡ��ʵ��
     *
     * @return
     */
    public static DESedeWithSHA getInstance()
    {
        return new DESedeWithSHA();
    }

    /**
     * ȡ��ʵ��
     *
     * @return
     */
    public static DESedeWithSHA getInstance(String algorithm)
    {
        return new DESedeWithSHA(algorithm);
    }

    /**
     * ����SHA�㷨���ժҪ
     * @param msg String
     * @return byte[]
     */
    public byte[] sha(String msg)
    {
        try
        {
            MessageDigest msgDig = MessageDigest.getInstance("SHA-1");
            msgDig.update(msg.getBytes());
            return msgDig.digest();
        }
        catch (java.security.NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ����ݼ���
     * @param msg String
     * @param keyFileName String
     * @return String
     */
    public byte[] encrypt(String msg, String key)
    {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        byte[] plainText = null; //����

        try
        {

            //����ϢתΪ�ֽ�
            byte[] bt = msg.getBytes("GB2312");

            //����ܳ�
            SecretKey deskey = new SecretKeySpec(key.getBytes(), Algorithm);

            //����
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            plainText = c1.doFinal(bt);
            bt = null;
        }
        catch (java.security.NoSuchAlgorithmException e1)
        {
            e1.printStackTrace();
        }
        catch (javax.crypto.NoSuchPaddingException e2)
        {
            e2.printStackTrace();
        }
        catch (java.lang.OutOfMemoryError e3)
        {
            e3.printStackTrace();
        }
        catch (Exception e4)
        {
            e4.printStackTrace();
        }
        return plainText;
    }

    /**
     * ����ݽ���
     * @param plainText String
     * @param keyFileName String
     * @return String
     */
    public String decrypt(byte[] plainText, String key)
    {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        byte[] msg = null;
        try
        {
            //�ܳ�
            SecretKey deskey = new SecretKeySpec(key.getBytes(), Algorithm);

            //System.out.println(deskey.getEncoded().length);
            // System.out.println(new String(deskey.getEncoded()));

            //����
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            msg = c1.doFinal(plainText);
        }
        catch (java.security.NoSuchAlgorithmException e1)
        {
            e1.printStackTrace();
        }
        catch (javax.crypto.NoSuchPaddingException e2)
        {
            e2.printStackTrace();
        }
        catch (java.lang.Exception e3)
        {
            e3.printStackTrace();
        }
	
	String temp = "";
	try
	{
	    temp = new String(msg, "GB2312");
	}
	catch(Exception e)
	{}
        return temp;
    }

    public static void main(String[] args)
    {
        //test DESedeWithSHA

        //DESedeWithSHA enc = DESedeWithSHA.getInstance();
        //String a = "";
        //System.out.println(System.getProperty("file.separator"));
        //String key = "e0d4d849f8aec62b60a05261";
        //String a = Encrypt.encrypt("��ϲ����Խ�����", key);
       // String b = enc.decrypt(a, key);
        //System.out.println(a);
        //writeToFile(a, new File("c:\\temp\\data"));


    }

    public static void writeToFile(byte[] datas, File file)
    {
        try
        {
            FileOutputStream wr = new FileOutputStream(file);
            wr.write(datas);
            wr.flush();
            wr.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}


