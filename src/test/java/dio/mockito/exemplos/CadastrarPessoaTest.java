package dio.mockito.exemplos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class CadastrarPessoaTest {

    @Mock
    private ApiDosCorreios apiDosCorreios;

    @InjectMocks
    private CadastrarPessoa cadastrarPessoa;

    @Test
    void lancarExcecaoQuandoChamarApiDosCorreios() {
        Mockito.when(apiDosCorreios.buscaDadosComBaseNoCep(anyString())).thenThrow(IllegalArgumentException.class);

        Assertions.assertThrows(IllegalArgumentException.class , () ->
                cadastrarPessoa.cadastrarPessoa("João", "98656554654654", LocalDate.of(2005, 1, 30), "24900120"));

    }

    @Test
    void validarDadosDeCadastro() {
        DadosLocalizacao dadosLocalizacao = new DadosLocalizacao("RJ", "Maricá", "Av Beira Rio", "antiga rua do canal", "Centro");
        Mockito.when(apiDosCorreios.buscaDadosComBaseNoCep("24900120")).thenReturn(dadosLocalizacao);
        Pessoa pessoa = cadastrarPessoa.cadastrarPessoa("João", "98656554654654", LocalDate.of(2005, 1, 30), "24900120");

        assertEquals("João", pessoa.getNome());
        assertEquals("98656554654654", pessoa.getDocumento());
        assertEquals(LocalDate.of(2005, 1, 30), pessoa.getNascimento());
        assertEquals("RJ", pessoa.getEndereco().getUf());
        assertEquals("Maricá", pessoa.getEndereco().getCidade());

    }

}
