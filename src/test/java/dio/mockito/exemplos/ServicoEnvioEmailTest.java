package dio.mockito.exemplos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ServicoEnvioEmailTest {

    @Mock
    private PlataformaDeEnvio plataformaDeEnvio;

    @InjectMocks
    private ServicoEnvioEmail servicoEnvioEmail;

    @Captor
    private ArgumentCaptor<Email> captorEmail;

    @Test
    void validarDadosEnviadosParaPlataformaDeEnvio() {
        String enderecoEmail = "wall@wall.com";
        String mensagem = "Olá Wall";
        boolean ehFormatoHtml = false;

        servicoEnvioEmail.enviaEmail(enderecoEmail, mensagem,  ehFormatoHtml);

        Mockito.verify(plataformaDeEnvio).enviaEmail(captorEmail.capture());


        Email emailCapturado = captorEmail.getValue();

        Assertions.assertEquals(enderecoEmail, emailCapturado.getEnderecoEmail());
        Assertions.assertEquals(mensagem, emailCapturado.getMensagem());
        Assertions.assertEquals(Formato.TEXTO, emailCapturado.getFormato());




    }

}
