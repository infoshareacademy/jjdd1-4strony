package isacademy.jjdd1.czterystrony.webapp.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import isacademy.jjdd1.czterystrony.webapp.persistence.queries.PensionFundRatingQueries;

import javax.persistence.*;

@Entity
@Table(indexes = {@Index(
        name = "UX_PensionFundRating_id_date_pensionFund_id",
        columnList = "id,date,pensionFund_id")
})
@NamedQueries({
        @NamedQuery(
                name = "PensionFundRating.getByPensionFundAndDate",
                query = "SELECT r FROM PensionFundRating r WHERE r.date = :date AND r.pensionFund = :pensionFund"
        ),
        @NamedQuery(
                name = "PensionFundRating.getAllByPensionFund",
                query = "SELECT r FROM PensionFundRating r WHERE r.pensionFund = :pensionFund ORDER BY r.date ASC"
        )
})
@NamedNativeQuery(
        name = "PensionFundRating.insertDataFromCsv",
        query = PensionFundRatingQueries.insertFromCsv
)
public class PensionFundRating extends Rating {

    @ManyToOne
    private PensionFund pensionFund;

    public PensionFundRating() {
    }

    public static class Builder extends Rating.Builder<PensionFundRating, Builder> {

        @Override
        PensionFundRating createRating() {
            return null;
        }

        @Override
        Builder createBuilder() {
            return null;
        }

        @Override
        public Builder withInstrument(FinancialInstrument instrument) {
            rating.pensionFund = (PensionFund) instrument;
            return builder;
        }
    }

    @JsonIgnore
    public PensionFund getPensionFund() {
        return pensionFund;
    }

    public void setPensionFund(PensionFund pensionFund) {
        this.pensionFund = pensionFund;
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
                ", pensionFund=" + pensionFund +
                '}';
    }
}
