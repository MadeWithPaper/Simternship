import java.util.*;
import java.math.BigDecimal;

/**
 * <h1>Resume</h1>
 * The Resume class stores the data for a resume that
 * a user uploads to the system. Other mechanisms are
 * required to extract the required data from an uploaded
 * resume or to display the resume in the UI.
 *
 * @author Joel Dentici
 * @version 0.1
 * @since 2018-01-15
 */
public class Resume
{
   public final String name;
   public final ContactInfo contactInfo;
   public final List<String> skills;
   public final List<Education> education;
   public final List<experience> experience;

   /**
    * Constructs a Resume.
    *
    * @param name The name on the resume
    * @param contactInfo The contact information for the person submitting the resume
    * @param skills The skills the person has
    * @param education The education the person has
    * @param experience The experience the person has
    */
   public Resume(String name, ContactInfo contactInfo, List<String> skills,
    List<Education> education, List<Experience> experience)
   {
      this.name = name;
      this.contactInfo = contactInfo;
      this.skills = Collections.unmodifiableList(skills);
      this.education = Collections.unmodifiableList(education);
      this.experience = Collections.unmodifiableList(experience);
   }

   /**
    * <h1>Resume.ContactInfo</h1>
    * The ContactInfo class stores the data for the contact information
    * included in a Resume.
    *
    * @author Joel Dentici
    * @version 0.1
    * @since 2018-01-15
    */
   static class ContactInfo
   {
      public final String email;
      public final String phone;

      /**
       * Constructs a ContactInfo
       *
       * @param email The email to contact the person with
       * @param phone The phone number to contact the person at.
       */
      public ContactInfo(String email, String phone)
      {
         this.email = email;
         this.phone = phone;

         validateEmail(email);
         validatePhone(phone);
      }

      /**
       * Validates an email address. If the address does
       * does not match the validation pattern, a runtime
       * exception is thrown.
       *
       * @param email The email address to check.
       */
      private void validateEmail(String email)
      {

      }

      /**
       * Validates a phone number. If the phone number
       * does match the validation pattern, a runtime exception
       * is thrown.
       *
       * @param phone The phone number to check.
       */
      private void validatePhone(String phone)
      {

      }
   }

   /**
    * <h1>Resume.Experience</h1>
    * The Experience class stores some experience that a person
    * has included on their Resume.
    *
    * @author Joel Dentici
    * @version 0.1
    * @since 2018-01-15
    */
   static class Experience
   {
      public final Date startDate;
      public final Date endDate;
      public final String companyName;
      public final String positionName;
      public final List<String> tasks;

      /**
       * Constructs an Experience
       *
       * @param companyName The company the experience was gained at.
       * @param positionName The position the person had within the company.
       * @param startDate The date the person started working at the company.
       * @param endDate The date the person stopped working at the company.
       * @param tasks The list of tasks the person performed for the company.
       */
      public Experience(String companyName, String positionName, 
         Date startDate, Date endDate, List<String> tasks)
      {
         this.companyName = companyName;
         this.positionName = positionName;
         this.startDate = startDate;
         this.endDate = endDate;
         this.tasks = tasks;
      }
   }

   /**
    * <h1>Resume.Education</h1>
    * The Education class stores some education that a person
    * has included on their Resume.
    *
    * @author Joel Dentici
    * @version 0.1
    * @since 2018-01-15
    */
   static class Education
   {
      public final Date startDate;
      public final Date endDate;
      public final String schoolName;
      public final String majorName;
      public final BigDecimal gpa;

      /**
       * Constructs an Education
       *
       * @param schoolName The school the person was educated at.
       * @param majorName The major the person completed at the school.
       * @param startDate The date the person started at the school.
       * @param endDate The date the person finished the school.
       * @param gpa The GPA attained by the person at the school.
       */
      public Education(String schoolName, String majorName, 
         Date startDate, Date endDate, BigDecimal gpa)
      {
         this.schoolName = schoolName;
         this.majorName = majorName;
         this.startDate = startDate;
         this.endDate = endDate;
         this.gpa = gpa;
      }
   }
}