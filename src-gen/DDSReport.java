

/*
WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

This file was generated from .idl 
using RTI Code Generator (rtiddsgen) version 3.1.0.
The rtiddsgen tool is part of the RTI Connext DDS distribution.
For more information, type 'rtiddsgen -help' at a command shell
or consult the Code Generator User's Manual.
*/

import com.rti.dds.infrastructure.*;
import com.rti.dds.infrastructure.Copyable;
import java.io.Serializable;
import com.rti.dds.cdr.CdrHelper;

public class DDSReport   implements Copyable, Serializable{

    public int timestamp = (int)0;
    public int VMnumber = (int)0;
    public int hostNumber = (int)0;
    public int DataCenterNumber = (int)0;

    public DDSReport() {

    }
    public DDSReport (DDSReport other) {

        this();
        copy_from(other);
    }

    public static java.lang.Object create() {

        DDSReport self;
        self = new  DDSReport();
        self.clear();
        return self;

    }

    public void clear() {

        timestamp = (int)0;
        VMnumber = (int)0;
        hostNumber = (int)0;
        DataCenterNumber = (int)0;
    }

    @Override
    public boolean equals(java.lang.Object o) {

        if (o == null) {
            return false;
        }        

        if(getClass() != o.getClass()) {
            return false;
        }

        DDSReport otherObj = (DDSReport)o;

        if(this.timestamp != otherObj.timestamp) {
            return false;
        }
        if(this.VMnumber != otherObj.VMnumber) {
            return false;
        }
        if(this.hostNumber != otherObj.hostNumber) {
            return false;
        }
        if(this.DataCenterNumber != otherObj.DataCenterNumber) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int __result = 0;
        __result += (int)timestamp;
        __result += (int)VMnumber;
        __result += (int)hostNumber;
        __result += (int)DataCenterNumber;
        return __result;
    }

    /**
    * This is the implementation of the <code>Copyable</code> interface.
    * This method will perform a deep copy of <code>src</code>
    * This method could be placed into <code>DDSReportTypeSupport</code>
    * rather than here by using the <code>-noCopyable</code> option
    * to rtiddsgen.
    * 
    * @param src The Object which contains the data to be copied.
    * @return Returns <code>this</code>.
    * @exception NullPointerException If <code>src</code> is null.
    * @exception ClassCastException If <code>src</code> is not the 
    * same type as <code>this</code>.
    * @see com.rti.dds.infrastructure.Copyable#copy_from(java.lang.Object)
    */
    public java.lang.Object copy_from(java.lang.Object src) {

        DDSReport typedSrc = (DDSReport) src;
        DDSReport typedDst = this;

        typedDst.timestamp = typedSrc.timestamp;
        typedDst.VMnumber = typedSrc.VMnumber;
        typedDst.hostNumber = typedSrc.hostNumber;
        typedDst.DataCenterNumber = typedSrc.DataCenterNumber;

        return this;
    }

    @Override
    public java.lang.String toString(){
        return toString("", 0);
    }

    public java.lang.String toString(java.lang.String desc, int indent) {
        java.lang.StringBuffer strBuffer = new java.lang.StringBuffer();

        if (desc != null) {
            CdrHelper.printIndent(strBuffer, indent);
            strBuffer.append(desc).append(":\n");
        }

        CdrHelper.printIndent(strBuffer, indent+1);        
        strBuffer.append("timestamp: ").append(this.timestamp).append("\n");  
        CdrHelper.printIndent(strBuffer, indent+1);        
        strBuffer.append("VMnumber: ").append(this.VMnumber).append("\n");  
        CdrHelper.printIndent(strBuffer, indent+1);        
        strBuffer.append("hostNumber: ").append(this.hostNumber).append("\n");  
        CdrHelper.printIndent(strBuffer, indent+1);        
        strBuffer.append("DataCenterNumber: ").append(this.DataCenterNumber).append("\n");  

        return strBuffer.toString();
    }

}
