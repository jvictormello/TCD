/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.controller;

import examples.telas.TelaPrincipalAco;

/**
 *
 * @author MarcosVinicius
 */
public class DuasSecoesChapaGrossaLateralController extends TelaPrincipalAco{
    
public double ValorFaxrk(double d,int fuk){ //Verificar como se utiliza esse parâmetro
    double valor1 = (0.75*((Math.PI*(Math.pow(d, 2)))/4)*fuk);
    
    return valor1;
}

public double ValorMyk(int fuk, double d){   
   double myk = (0.3*(fuk)*((Math.pow(d, 2.6))));
    return myk;
}

//Cálculo EQ 30 - Embutimento na peça de madeira
public double EmbMadeira(double fek,double t1,double d){
    double fvk11 = 0.5*fek*t1*d;
    return fvk11;
}

//Cálculo EQ 32 - //Cálculo EQ 31 - Flexão dos pinos com duas rótulas plásticas
public double DeformDuasRotulas(double fek,double d,double myk,double valorFaxrk){
    double johansen12 = (Math.sqrt(myk*fek*d));
    if (valorFaxrk >0){
        if (valorFaxrk < johansen12){
            return (2.3*johansen12) + (valorFaxrk/4);
        }
        else {
            return (2.3*johansen12) + (johansen12/4);
        }
    }
    return (2.3*johansen12);
   
}
}
