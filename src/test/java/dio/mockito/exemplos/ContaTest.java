package dio.mockito.exemplos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ContaTest {

    @Spy
    private Conta conta = new Conta(555);

    @Test
    void testeDebito() {

        Mockito.verifyNoInteractions(conta);

        conta.debita(100);
        Mockito.verify(conta).debita(100);

    }

    @Test
    void validarOrdemDeChamadas(){
        conta.pagaBoleto(350);

        InOrder inOrder = Mockito.inOrder(conta);
        inOrder.verify(conta).pagaBoleto(350);
        inOrder.verify(conta).validaSaldo(350);
        inOrder.verify(conta).debita(350);
        inOrder.verify(conta).enviaCreditoParaEmissor(350);

    }


    @Test
    void validarQuantidadeDeChamadas(){

        conta.validaSaldo(300);
        conta.validaSaldo(400);
        conta.validaSaldo(550);


        Mockito.verify(conta, Mockito.times(3)).validaSaldo(Mockito.anyInt());
    }




}
