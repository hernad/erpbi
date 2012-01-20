/*
 *   Given a Days date since 1900/01/01 return a date string yyy/MM/dd
 *
 *  Copyright © 2011 Jeff Gunderson
 *  Licensed under the GNU Public License, Version 3.0.
 *  You may obtain a copy of the GPL V3 License at http://www.gnu.org/licenses/lgpl-3.0.html
 *
*/

package com.erpi.mdxudf; 

import mondrian.olap.Evaluator;
import mondrian.olap.Syntax;
import mondrian.olap.type.Type;
import mondrian.olap.type.StringType;
import mondrian.olap.type.NumericType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class GetDateInfo implements mondrian.spi.UserDefinedFunction
{
    private String udfName;
    private String returnString;
    protected final Log log = LogFactory.getLog(this.getClass());

    public GetDateInfo(String name)
    {
        udfName = name;
        log.info("constructor, udfName: "+udfName);
    }

    public Object execute(Evaluator evaluator, Argument[] arguments)
    {
        synchronized(this)
        {
             if (udfName.equals("getDateString"))
             {
                // get input string as int
                Object oDays = arguments[0].evaluate(evaluator);
                String sDays = oDays.toString();
                float fDays = Float.parseFloat(sDays);
                int iDays = (int)fDays;
                //int iDays = Integer.parseInt(sDays);

                // create Calendar starting at 1900/01/01 and bump by input days
                Calendar c = Calendar.getInstance();
                c.set(1900, 00, 01);
                c.add(Calendar.DATE, iDays);  

                // fomat date string
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                returnString = sdf.format(c.getTime());  
             }  
            else
             {
                    returnString = "";
             }

            if (log.isDebugEnabled())
            {
                log.debug(" returnString: "+returnString);
            }

        return returnString;
        }
    }


    public String getDescription() {
        return "Date functions with Days since 1900 input";
    }

    public String getName() {
        return udfName;
    }

    public Type[] getParameterTypes() {
        return new Type[] {
            new StringType()
        };
    }

    public String[] getReservedWords() {
        return null;
    }

    public Type getReturnType(Type[] parameterTypes) {
        return new StringType();
    }

    public Syntax getSyntax() {
        return Syntax.Function;
    }

}
