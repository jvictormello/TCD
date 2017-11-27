/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcd.controller;

import br.com.tcd.telas.TelaPrincipalAco;

/**
 *
 * @author MarcosVinicius
 */
public class DuasSecoesChapaCentralController extends TelaPrincipalAco{


public double ValorFaxrk(double d,int fuk,double d2,double d1,double fek, double arruela){ //Verificar como se utiliza esse parâmetro
    double valor1 = (0.75*((Math.PI*(Math.pow(d, 2)))/4)*fuk);
    //print 'Faxuk (Resistência de tração do parafuso) = ', round(valor1,5)
    double valor2 = 0;
    if (arruela == 1.0){
        valor2 = ((((Math.PI*(Math.pow(d2,2))/4)-((Math.PI*(Math.pow(d1,2))/4)))*3*fek));
        //print 'Faxuk (Resistência ao embutimento da arruela na madeira) = ', round(valor2,5)
    }
    if (arruela == 2.0){
        valor2 = ((((Math.PI*(Math.pow(d2,2))/4)-(Math.pow(d1,2)))*3*fek));

    }
    if (arruela == 3.0){
        valor2 = (((Math.pow(d2,2)-(Math.pow(d1,2)))*3*fek));

    }

    return (valor1 <= valor2)?valor1:valor2;
}

public double ValorMyk(int fuk, double d){   
   double myk = (0.3*(fuk)*((Math.pow(d, 2.6))));
    return myk;
}

//Cálculo EQ 30 - Embutimento nas peças de madeira
public double EmbMadeira(double fek,double t1,double d){
    double fvk6 = (fek)*t1*d;
    return fvk6;
}

//Cálculo EQ 31 - Flexão dos pinos com uma rótula plástica
public double DeformUmaRotula(double fek, double d, double t1,double valorFaxrk,double myk){ //Multiplica-se por 10 para igualar as unidades e resultar em (N)
    double johansen7 = (fek*t1*d)*((Math.sqrt(2+((4*myk)/(fek*(Math.pow(t1, 2))*d))))-1);
    if (valorFaxrk >0){
        if (valorFaxrk < johansen7){
            return johansen7 + (valorFaxrk/4);
        }
        else {
            return johansen7 + (johansen7/4);
        }
    }
    return johansen7;
    
}

//Cálculo EQ 32 - //Cálculo EQ 31 - Flexão dos pinos com duas rótulas plásticas
public double DeformDuasRotulas(double fek,double d,double myk,double valorFaxrk){
    double johansen8 = (Math.sqrt(myk*fek*d));
    if (valorFaxrk >0){
        if (valorFaxrk < johansen8){
            return (2.3*johansen8) + (valorFaxrk/4);
        }
        else {
            return (2.3*johansen8) + (johansen8/4);
        }
    }
    return (2.3*johansen8);
   
}
}
