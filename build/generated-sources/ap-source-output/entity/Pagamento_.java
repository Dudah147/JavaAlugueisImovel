package entity;

import entity.Locacao;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-06-21T19:48:23", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Pagamento.class)
public class Pagamento_ { 

    public static volatile SingularAttribute<Pagamento, Integer> idPagamento;
    public static volatile CollectionAttribute<Pagamento, Locacao> locacaoCollection;
    public static volatile SingularAttribute<Pagamento, String> descricao;

}