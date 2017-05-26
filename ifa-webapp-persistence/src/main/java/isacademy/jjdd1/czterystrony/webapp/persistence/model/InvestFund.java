package isacademy.jjdd1.czterystrony.webapp.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import isacademy.jjdd1.czterystrony.webapp.persistence.queries.InvestFundQueries;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = {@Index(
        name = "UX_InvestFund_id_name",
        columnList = "id,name")
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "InvestFundDetailsMapping",
                classes = {
                        @ConstructorResult(
                                targetClass = InvestFundDetails.class,
                                columns = {
                                        @ColumnResult(name = "name", type = String.class),
                                        @ColumnResult(name = "id", type = String.class),
                                        @ColumnResult(name = "priority", type = int.class),
                                        @ColumnResult(name = "date", type = LocalDate.class),
                                        @ColumnResult(name = "close", type = BigDecimal.class),
                                        @ColumnResult(name = "diff", type = BigDecimal.class)
                                }
                        )}),
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "InvestFund.getAllWithDetails",
                query = InvestFundQueries.allWithDetails,
                resultSetMapping = "InvestFundDetailsMapping"),
        @NamedNativeQuery(
                name = "InvestFund.getByIdWithDetails",
                query = InvestFundQueries.byIdWithDetails,
                resultSetMapping = "InvestFundDetailsMapping"),
})
public class InvestFund extends FinancialInstrument {

    @Min(0)
    @Max(100)
    @NotNull
    private int priority;

    @JsonIgnore
    @OneToMany(mappedBy = "investFund", cascade = CascadeType.PERSIST)
    private List<InvestFundRating> ratings = new ArrayList<>();

    public InvestFund() {
    }

    public static class Builder extends FinancialInstrument.Builder<InvestFund, Builder> {

        @Override
        InvestFund createInstrument() {
            return new InvestFund();
        }

        @Override
        Builder createBuilder() {
            return this;
        }
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public List<InvestFundRating> getRatings() {
        return ratings;
    }

    @Override
    public String toString() {
        return "InvestFund{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastRatingDate=" + lastRatingDate +
                ", priority=" + priority +
                '}';
    }
}
