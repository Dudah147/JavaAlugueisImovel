package entity;

import entity.Imovel;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-06-10T16:23:56", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Tipoimovel.class)
public class Tipoimovel_ { 

    public static volatile CollectionAttribute<Tipoimovel, Imovel> imovelCollection;
    public static volatile SingularAttribute<Tipoimovel, Integer> idTipoImovel;
    public static volatile SingularAttribute<Tipoimovel, String> descricao;

}