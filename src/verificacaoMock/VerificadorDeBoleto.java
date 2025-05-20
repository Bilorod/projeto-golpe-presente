package verificacao;

// Interface que define o contrato para qualquer verificador de boletos
public interface VerificadorDeBoleto {
    //Metodo que todos os verificadores devem implementar
    boolean verificar(String codigoDeBarras);
}
