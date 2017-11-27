/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.modelo;

/**
 *
 * @author MarcosVinicius
 */
public class DuasSecoesChapaFinaLateralController {
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
    double fvk9 = 0.5*fek*t1*d;
    return fvk9;
}

//Cálculo EQ 31 - Flexão dos pinos com uma rótula plástica
public double DeformUmaRotula(double fek,double d,double myk,double valorFaxrk){
    double johansen10 = (Math.sqrt(2*myk*fek*d));
    if (valorFaxrk >0){
        if (valorFaxrk < johansen10){
            return (1.15*johansen10) + (valorFaxrk/4);
        }
        else {
            return (1.15*johansen10) + (johansen10/4);
        }
    }
    return (1.15*johansen10);
   
}
}
