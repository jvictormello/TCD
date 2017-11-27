/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.controller;

/**
 *
 * @author MarcosVinicius and GabrielaMello
 */
public class UmaSecaoCorteParaleloController{

public double ValorFaxrk(double d,int fuk,double d2,double d1,double fe90d1, double arruela){ //Verificar como se utiliza esse parâmetro
    double valor1 = (0.75*((Math.PI*(Math.pow(d, 2)))/4)*fuk);
    //print 'Faxuk (Resistência de tração do parafuso) = ', round(valor1,5)
    double valor2 = 0;
    if (arruela == 1.0){
        valor2 = ((((Math.PI*(Math.pow(d2,2))/4)-((Math.PI*(Math.pow(d1,2))/4)))*3*fe90d1));
        //print 'Faxuk (Resistência ao embutimento da arruela na madeira) = ', round(valor2,5)
    }
    if (arruela == 2.0){
        valor2 = ((((Math.PI*(Math.pow(d2,2))/4)-(Math.pow(d1,2)))*3*fe90d1));

    }
    if (arruela == 3.0){
        valor2 = (((Math.pow(d2,2)-(Math.pow(d1,2)))*3*fe90d1));

    }
    return (valor1 <= valor2)?valor1:valor2;
}

public double ValorFaxrkPrego(double d,int fuk){ //Verificar como se utiliza esse parâmetro
    double valorP = (0.75*((Math.PI*(Math.pow(d, 2)))/4)*fuk);
    //print 'Faxuk (Resistência de tração do parafuso) = ', round(valor1,5)
    
    return valorP;
}


public double ValorMyd(int fuk, double d){   
   double myd = (0.3*(fuk)*((Math.pow(d, 2.6)))); //Multiplica-se o d por 10 para deixar o momento em N.mm
    //System.out.println("Md ="+ myd);
    return myd;
}


//Cálculo EQ 24 - Embutimento menor peça de madeira
public double EmbMenorPeca(double fe0d1,double t1,double d){
    double rd1 = (fe0d1)*t1*d;
    return rd1;
}

//Cálculo EQ 25 - Embutimento maior peça de madeira
public double EmbMaiorPeca(double fe0d1,double t2,double d, double beta){
    double rd2 = (fe0d1)*t2*d*beta;
    return rd2;

}

//Cálculo EQ 26 - Embutimento nas duas peças
public double EmbDuasPecas(double fe0d1, double d, double t1, double t2, double beta, double valorFaxrk, double valorFaxrkPrego){ //Multiplica-se por 10 para igualar as unidades e resultar em (N)
    double johansen3 = (((fe0d1*t1*d)/(1+beta))*((Math.sqrt(beta+(2*(Math.pow(beta, 2))*(1+(t2/t1)+(Math.pow(t2/t1, 2))))+(Math.pow(beta, 3)*Math.pow(t2/t1, 2))))-(beta*(1+(t2/t1)))));
    if (valorFaxrk >0){
        if ((valorFaxrk/4) < (0.25*johansen3)){
            return johansen3 + (valorFaxrk/4);
        }
        else {
            return johansen3 + (0.25*johansen3);
        }
        
    }
    
    if (valorFaxrkPrego >0){
        if ((valorFaxrkPrego/4) < (0.15*johansen3)){
            return johansen3 + (valorFaxrkPrego/4);
        }
        else {
            return johansen3 + (0.15*johansen3);
        }
    }
    return johansen3;
    
    
}


//Cálculo EQ 27 - Embutimento nas duas peças e flexao do pino na maior
public double DeformPinoMaiorPeca(double fe0d1,double t1,double d,double beta,double myd,double valorFaxrk, double valorFaxrkPrego){
    double johansen4 = (((fe0d1*t1*d)/(2+beta))*((Math.sqrt((2*beta*(1+beta))+((4*beta*(2+beta)*myd)/(fe0d1*d*(Math.pow(t1, 2))))))-beta));
    if (valorFaxrk >0){
        if ((valorFaxrk/4) < (0.25*johansen4)){
            return (1.05*johansen4) + (valorFaxrk/4);
        }
        else {
            return (1.05*johansen4) + (0.25*johansen4);
        }
    }
    
    if (valorFaxrkPrego >0){
        if ((valorFaxrkPrego/4) < (0.15*johansen4)){
            return (1.05*johansen4) + (valorFaxrkPrego/4);
        }
        else {
            return (1.05*johansen4) + (0.15*johansen4);
        }
    }
    return (1.05*johansen4);
   
}

//Cálculo EQ 28 - Embutimento nas duas peças e flexao do pino na menor
public double DeformPinoMenorPeca(double fe0d1,double t2,double d,double beta,double myd,double valorFaxrk, double valorFaxrkPrego){
    double johansen5 = (((fe0d1*t2*d)/(1+(2*beta)))*((Math.sqrt((2*(Math.pow(beta, 2))*(1+beta))+((4*beta*(1+2*beta)*myd)/(fe0d1*d*(Math.pow(t2, 2)))))-beta)));
    if (valorFaxrk >0){
        if ((valorFaxrk/4) < (0.25*johansen5)){
            return (1.05*johansen5) + (valorFaxrk/4);
        }
        else {
            return (1.05*johansen5) + (0.25*johansen5);
        }
    }
    
    if (valorFaxrkPrego >0){
        if ((valorFaxrkPrego/4) < (0.15*johansen5)){
            return (1.05*johansen5) + (valorFaxrkPrego/4);
        }
        else {
            return (1.05*johansen5) + (0.15*johansen5);
        }
    }
    return (1.05*johansen5);
}

//Cálculo EQ 29 - Embutimento nas duas peças e flexao nas duas
public double DeformPinoNasDuas(double beta,double myd,double fe0d1,double d,double valorFaxrk, double valorFaxrkPrego){
    double johansen6 = (Math.sqrt((2*beta)/(1+beta)))*(Math.sqrt(2*myd*fe0d1*d));
    if (valorFaxrk >0){
        if ((valorFaxrk/4) < (0.25*johansen6)){
            return (1.15*johansen6) + (valorFaxrk/4);
        }
        else {
            return (1.15*johansen6) + (0.25*johansen6);
        }
    }
    
    if (valorFaxrkPrego >0){
        if ((valorFaxrkPrego/4) < (0.15*johansen6)){
            return (1.15*johansen6) + (valorFaxrkPrego/4);
        }
        else {
            return (1.15*johansen6) + (0.15*johansen6);
        }
    }
    return (1.15*johansen6);

}    

}
