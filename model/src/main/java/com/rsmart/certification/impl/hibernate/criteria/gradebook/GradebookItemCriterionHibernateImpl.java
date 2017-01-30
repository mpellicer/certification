package com.rsmart.certification.impl.hibernate.criteria.gradebook;

import com.rsmart.certification.impl.hibernate.criteria.AbstractCriterionHibernateImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.sakaiproject.service.gradebook.shared.Assignment;

/**
 * User: duffy
 * Date: Jul 18, 2011
 * Time: 10:51:55 PM
 */
public class GradebookItemCriterionHibernateImpl extends AbstractCriterionHibernateImpl
{
    protected static final SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");

    protected final static String ASSIGNMENT_ID = "gradebook.item";
    protected final static String ASSIGNMENT_NAME = "gradebook.item.name";
    protected final static String ASSIGNMENT_DUE = "gradebook.item.due";
    protected final static String ASSIGNMENT_POINTS = "gradebook.item.points";

    public void setAssignment(Assignment assn)
    {
        setItemId(assn.getId());
        setItemName(assn.getName());
        setDueDate(assn.getDueDate());
        setItemPoints(assn.getPoints());
    }

    public Date getDueDate()
    {
        String dateStr = getVariableBindings().get(ASSIGNMENT_DUE);
        if (dateStr == null)
        {
            return null;
        }

        try
        {
            return dateFormat.parse(dateStr);
        }
        catch (ParseException e)
        {
            //log this
            return null;
        }
    }

    public void setDueDate (Date due)
    {
        if (due == null)
        {
            getVariableBindings().remove(ASSIGNMENT_DUE);
        }
        else
        {
            getVariableBindings().put(ASSIGNMENT_DUE, dateFormat.format(due));
        }
    }

    public Long getItemId()
    {
        String itemStr = getVariableBindings().get(ASSIGNMENT_ID);
        if (itemStr == null)
        {
            return null;
        }

        return (Long.parseLong(itemStr));
    }

    public void setItemId(Long itemId)
    {
        getVariableBindings().put(ASSIGNMENT_ID, Long.toString(itemId));
    }

    public String getItemName()
    {
        return getVariableBindings().get(ASSIGNMENT_NAME);
    }

    public void setItemName(String name)
    {
        getVariableBindings().put(ASSIGNMENT_NAME, name);
    }

    public Double getItemPoints()
    {
        String ptsStr = getVariableBindings().get(ASSIGNMENT_POINTS);
        return Double.parseDouble(ptsStr);
    }

    public void setItemPoints(Double points)
    {
        getVariableBindings().put(ASSIGNMENT_POINTS, points.toString());
    }

    @Override
    public List<String> getReportHeaders()
    {
        List<String> reportHeaders = new ArrayList<String>();
        String header = getItemName();
        reportHeaders.add(header);
        return reportHeaders;
    }

    //OWLTODO: Implement
    @Override
    public List<String> getReportData()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
