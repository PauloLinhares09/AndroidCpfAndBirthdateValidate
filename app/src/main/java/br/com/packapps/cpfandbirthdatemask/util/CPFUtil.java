package br.com.packapps.cpfandbirthdatemask.util;

/**
 * Created by paulolinhares on 19/10/17.
 */

public class CPFUtil {
    public static boolean validateCpf(String cpf){
        //Verify if has 11 digits
        String cpfClean = cpf.replace(".", "");
        cpfClean = cpfClean.replace("-", "");
        if (cpfClean.length() != 11)
            return false;

        //Verify if is a number
        try{
            double number = Double.valueOf(cpfClean);
        }catch (Exception e){
            return false;
        }

        //continue
        int cpfArray[] = new int[11], dv1=0, dv2=0;
        if (cpf.length() != 11) {
            return false;
        }

        for(int i=0;i<11;i++)
            cpfArray[i] = Integer.parseInt(cpf.substring(i, i+1));
        for(int i=0;i<9;i++)
            dv1 += cpfArray[i] * (i+1);
        cpfArray[9] = dv1 = dv1 % 11;
        for(int i=0;i<10;i++)
            dv2 += cpfArray[i] * i;
        cpfArray[10] = dv2 = dv2 % 11;
        if(dv1>9) cpfArray[9]=0;		if(dv2>9) cpfArray[10]=0;

        if(Integer.parseInt(cpf.substring(9,10))!= cpfArray[9]||
                Integer.parseInt(cpf.substring(10,11))!=cpfArray[10])
            return false;
        else
            return true;
    }
}
