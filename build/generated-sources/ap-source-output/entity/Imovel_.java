package entity;

import entity.Tipoimovel;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-06-10T16:23:56", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Imovel.class)
public class Imovel_ { 

    public static volatile SingularAttribute<Imovel, String> endereco;
    public static volatile SingularAttribute<Imovel, Integer> quantBanheiros;
    public static volatile SingularAttribute<Imovel, Double> valorLocacao;
    public static volatile SingularAttribute<Imovel, String> fotoImovel;
    public static volatile SingularAttribute<Imovel, String> descricaoDependencias;
    public static volatile SingularAttribute<Imovel, Tipoimovel> idTipoImovel;
    public static volatile SingularAttribute<Imovel, Integer> idImovel;
    public static volatile SingularAttribute<Imovel, Double> metragem;
    public static volatile SingularAttribute<Imovel, Integer> quantQuartos;

}