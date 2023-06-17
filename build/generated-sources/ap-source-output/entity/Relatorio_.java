package entity;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-06-17T13:18:34", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Relatorio.class)
public class Relatorio_ { 

    public static volatile SingularAttribute<Relatorio, Double> valorLocacao;
    public static volatile SingularAttribute<Relatorio, Date> dataTermino;
    public static volatile SingularAttribute<Relatorio, Integer> idImovel;
    public static volatile SingularAttribute<Relatorio, Integer> idLocatario;

}