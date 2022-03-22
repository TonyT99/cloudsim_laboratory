

/*
WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

This file was generated from .idl
using RTI Code Generator (rtiddsgen) version 3.1.0.
The rtiddsgen tool is part of the RTI Connext DDS distribution.
For more information, type 'rtiddsgen -help' at a command shell
or consult the Code Generator User's Manual.
*/

package org.cloudsimplus.examples.dds_classes.datacentersimple;

import com.rti.dds.infrastructure.Copyable;
import java.io.Serializable;
import com.rti.dds.cdr.CdrHelper;

public class DdsDatacenterSimple implements Copyable, Serializable{

    public int id = (int)0;
    public int timestampOfReport = (int)0;

    public DdsDatacenterSimple() {

    }

    public DdsDatacenterSimple(int id, int timestamp) {
        this.id = id;
        this.timestampOfReport = timestamp;
    }
    public DdsDatacenterSimple(DdsDatacenterSimple other) {

        this();
        copy_from(other);
    }

    public static java.lang.Object create() {

        DdsDatacenterSimple self;
        self = new DdsDatacenterSimple();
        self.clear();
        return self;

    }

    public void clear() {

        id = (int)0;
        timestampOfReport = (int)0;
    }

    @Override
    public boolean equals(java.lang.Object o) {

        if (o == null) {
            return false;
        }

        if(getClass() != o.getClass()) {
            return false;
        }

        DdsDatacenterSimple otherObj = (DdsDatacenterSimple)o;

        if(this.id != otherObj.id) {
            return false;
        }
        if(this.timestampOfReport != otherObj.timestampOfReport) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int __result = 0;
        __result += (int)id;
        __result += (int)timestampOfReport;
        return __result;
    }

    /**
    * This is the implementation of the <code>Copyable</code> interface.
    * This method will perform a deep copy of <code>src</code>
    * This method could be placed into <code>datacentersimpleTypeSupport</code>
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

        DdsDatacenterSimple typedSrc = (DdsDatacenterSimple) src;
        DdsDatacenterSimple typedDst = this;

        typedDst.id = typedSrc.id;
        typedDst.timestampOfReport = typedSrc.timestampOfReport;

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
        strBuffer.append("id: ").append(this.id).append("\n");
        CdrHelper.printIndent(strBuffer, indent+1);
        strBuffer.append("timestampOfReport: ").append(this.timestampOfReport).append("\n");

        return strBuffer.toString();
    }

}
