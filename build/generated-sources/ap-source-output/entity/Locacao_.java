package entity;

import entity.Imovel;
import entity.Locatario;
import entity.Pagamento;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-06-21T19:48:23", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Locacao.class)
public class Locacao_ { 

    public static volatile SingularAttribute<Locacao, Pagamento> idPagamento;
    public static volatile SingularAttribute<Locacao, Double> valorDesconto;
    public static volatile SingularAttribute<Locacao, Date> dataTermino;
    public static volatile SingularAttribute<Locacao, Date> dataInicio;
    public static volatile SingularAttribute<Locacao, Imovel> idImovel;
    public static volatile SingularAttribute<Locacao, Integer> idLocacao;
    public static volatile SingularAttribute<Locacao, Boolean> encerrado;
    public static volatile SingularAttribute<Locacao, Locatario> idLocatario;

}