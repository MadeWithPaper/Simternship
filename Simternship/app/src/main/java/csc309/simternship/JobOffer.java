package csc309.simternship;

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
    public final String companyName;
    public final BigDecimal salary;

    /**
     * Constructs a JobOffer
     *
     * @param companyName The name of the company the offer is for
     * @param salary The salary the company has offered
     */
    public JobOffer(String companyName, BigDecimal salary)
    {
        this.companyName = companyName;
        this.salary = salary;
    }
}