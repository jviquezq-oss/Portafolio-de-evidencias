package Entidades;

public enum Nacionalidad {

    COSTARRICENSE("Costarricense"),
    ESTADOUNIDENSE("Estadounidense"),
    CANADIENSE("Canadiense"),
    MEXICANA("Mexicana"),
    GUATEMALTECA("Guatemalteca"),
    HONDURENA("Hondureña"),
    SALVADORENA("Salvadoreña"),
    NICARAGUENSE("Nicaragüense"),
    PANAMENA("Panameña"),
    COLOMBIANA("Colombiana"),
    VENEZOLANA("Venezolana"),
    ECUATORIANA("Ecuatoriana"),
    PERUANA("Peruana"),
    BOLIVIANA("Boliviana"),
    CHILENA("Chilena"),
    ARGENTINA("Argentina"),
    URUGUAYA("Uruguaya"),
    PARAGUAYA("Paraguaya"),
    BRASILEÑA("Brasileña"),
    ESPAÑOLA("Española"),
    FRANCESA("Francesa"),
    ALEMANA("Alemana"),
    ITALIANA("Italiana"),
    PORTUGUESA("Portuguesa"),
    BRITANICA("Británica"),
    IRLANDESA("Irlandesa"),
    HOLANDESA("Holandesa"),
    BELGA("Belga"),
    SUIZA("Suiza"),
    AUSTRIACA("Austriaca"),
    SUECA("Sueca"),
    NORUEGA("Noruega"),
    FINLANDESA("Finlandesa"),
    DANESA("Danesa"),
    RUSA("Rusa"),
    UCRANIANA("Ucraniana"),
    JAPONESA("Japonesa"),
    CHINA("China"),
    COREANA("Coreana"),
    INDIA("India"),
    AUSTRALIANA("Australiana"),
    NEOZELANDESA("Neozelandesa"),
    SUDAFRICANA("Sudafricana"),
    CUBANA("Cubana"),
    DOMINICANA("Dominicana"),
    PUERTORRIQUEÑA("Puertorriqueña"),
    JAMAJQUINA("Jamaiquina"),
    OTRA("Otra");

    private final String descripcion;

    Nacionalidad(String descripcion) {

        this.descripcion = descripcion;

    }

    @Override
    public String toString() {

        return descripcion;

    }

}