
package ddsgen;
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

public class Cloudlet   implements Copyable, Serializable{

    public int ttl = (int)0;
    public int peNumber = (int)0;
    public double utilizationModelParam = (double)0;
    public String modelType= (String)""; /* maximum length = (255) */

    public Cloudlet() {

    }
    public Cloudlet (Cloudlet other) {

        this();
        copy_from(other);
    }

    public static java.lang.Object create() {

        Cloudlet self;
        self = new  Cloudlet();
        self.clear();
        return self;

    }

    public void clear() {

        ttl = (int)0;
        peNumber = (int)0;
        utilizationModelParam = (int)0;
        modelType = (String)"";
    }

    @Override
    public boolean equals(java.lang.Object o) {

        if (o == null) {
            return false;
        }

        if(getClass() != o.getClass()) {
            return false;
        }

        Cloudlet otherObj = (Cloudlet)o;

        if(this.ttl != otherObj.ttl) {
            return false;
        }
        if(this.peNumber != otherObj.peNumber) {
            return false;
        }
        if(this.utilizationModelParam != otherObj.utilizationModelParam) {
            return false;
        }
        if(!this.modelType.equals(otherObj.modelType)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int __result = 0;
        __result += (int)ttl;
        __result += (int)peNumber;
        __result += (int)utilizationModelParam;
        __result += modelType.hashCode();
        return __result;
    }

    /**
    * This is the implementation of the <code>Copyable</code> interface.
    * This method will perform a deep copy of <code>src</code>
    * This method could be placed into <code>CloudletTypeSupport</code>
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

        Cloudlet typedSrc = (Cloudlet) src;
        Cloudlet typedDst = this;

        typedDst.ttl = typedSrc.ttl;
        typedDst.peNumber = typedSrc.peNumber;
        typedDst.utilizationModelParam = typedSrc.utilizationModelParam;
        typedDst.modelType = typedSrc.modelType;

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
        strBuffer.append("ttl: ").append(this.ttl).append("\n");
        CdrHelper.printIndent(strBuffer, indent+1);
        strBuffer.append("peNumber: ").append(this.peNumber).append("\n");
        CdrHelper.printIndent(strBuffer, indent+1);
        strBuffer.append("utilizationModelParam: ").append(this.utilizationModelParam).append("\n");
        CdrHelper.printIndent(strBuffer, indent+1);
        strBuffer.append("modelType: ").append(this.modelType).append("\n");

        return strBuffer.toString();
    }

}
