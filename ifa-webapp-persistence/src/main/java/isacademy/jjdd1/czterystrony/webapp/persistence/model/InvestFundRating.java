package isacademy.jjdd1.czterystrony.webapp.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import isacademy.jjdd1.czterystrony.webapp.persistence.queries.InvestFundRatingQueries;

import javax.persistence.*;

@Entity
@Table(indexes = {@Index(
        name = "UX_Rating_id_date_investFund_id",
        columnList = "id,date,investFund_id")
})
@NamedQueries({
        @NamedQuery(
                name = "Rating.getByFundAndDate",
                query = "SELECT r FROM InvestFundRating r WHERE r.date = :date AND r.investFund = :investFund"
        ),
        @NamedQuery(
                name = "Rating.getByFundInTimeRange",
                query = "SELECT r FROM InvestFundRating r WHERE r.investFund = :investFund AND r.date >= :startDate AND r.date <= :endDate"
        ),
        @NamedQuery(
                name = "Rating.getOldestForFund",
                query = "SELECT r FROM InvestFundRating r WHERE r.investFund = :investFund ORDER BY r.date ASC"
        ),
        @NamedQuery(
                name = "Rating.getNewestForFund",
                query = "SELECT r FROM InvestFundRating r WHERE r.investFund = :investFund ORDER BY r.date DESC"
        ),
        @NamedQuery(
                name = "Rating.getAllByFund",
                query = "SELECT r FROM InvestFundRating r WHERE r.investFund = :investFund ORDER BY r.date ASC"
        )
})
@NamedNativeQuery(
        name = "Rating.insertDataFromCsv",
        query = InvestFundRatingQueries.insertFromCsv
)
public class InvestFundRating extends Rating {

    @ManyToOne
    private InvestFund investFund;

    public InvestFundRating() {
    }

    public static class Builder extends Rating.Builder<InvestFundRating, Builder> {

        @Override
        InvestFundRating createRating() {
            return new InvestFundRating();
        }

        @Override
        Builder createBuilder() {
            return this;
        }

        @Override
        public Builder withInstrument(FinancialInstrument instrument) {
            rating.investFund = (InvestFund) instrument;
            return builder;
        }
    }

    @JsonIgnore
    public InvestFund getInvestFund() {
        return investFund;
    }

    public void setInvestFund(InvestFund investFund) {
        this.investFund = investFund;
    }

    @Override
    public String toString() {
        return "InvestFundRating{" +
                "id=" + id +
                ", date=" + date +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", investFund=" + investFund +
                '}';
    }
}
