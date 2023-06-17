package entity;

import entity.Imovel;
import entity.Locatario;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-06-17T13:18:34", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Locacao.class)
public class Locacao_ { 

    public static volatile SingularAttribute<Locacao, Double> valorDesconto;
    public static volatile SingularAttribute<Locacao, Integer> formaPgto;
    public static volatile SingularAttribute<Locacao, Date> dataTermino;
    public static volatile SingularAttribute<Locacao, Date> dataInicio;
    public static volatile SingularAttribute<Locacao, Imovel> idImovel;
    public static volatile SingularAttribute<Locacao, Integer> idLocacao;
    public static volatile SingularAttribute<Locacao, Boolean> encerrado;
    public static volatile SingularAttribute<Locacao, Locatario> idLocatario;

}