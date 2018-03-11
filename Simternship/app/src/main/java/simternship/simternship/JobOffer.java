package simternship.simternship;

import java.math.BigDecimal;

/**
 * <h1>Player.JobOffer</h1>
 * JobOffer objects represent a job offer the
 * player received.
 *
 * @author Joel Dentici
 * @version 0.1
 * @since 2018-01-16
 */
public class JobOffer
{
    public final Company company;
    public final BigDecimal salary;

    /**
     * Constructs a JobOffer
     *
     * @param companyName The name of the company the offer is for
     * @param salary The salary the company has offered
     */
    public JobOffer(Company company, BigDecimal salary)
    {
        this.company = company;
        this.salary = salary;
    }

    public boolean equals(Object other) {
        return other instanceof JobOffer &&
                this.equals((JobOffer) other);
    }

    @Override
    public int hashCode()
    {
       return super.hashCode();
    }
    public boolean equals(JobOffer other) {
        return company.getCompanyName().equals(other.getCompanyName())
                && salary.equals(other.salary);
    }

    public String getCompanyName()
    {
        return company.getCompanyName();
    }

    public BigDecimal getSalary()
    {
        return salary;
    }
}