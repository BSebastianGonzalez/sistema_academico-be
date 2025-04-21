package co.ufps.edu.backend.model;

import lombok.Getter;

@Getter
public enum TipoDocumento {
    CC("C.C"),
    CE("C.E"),
    TI("T.I");

    private final String sigla;

    TipoDocumento(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return sigla;
    }
}
