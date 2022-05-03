package ddsgen;
/*
WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

This file was generated from .idl
using RTI Code Generator (rtiddsgen) version 3.1.0.
The rtiddsgen tool is part of the RTI Connext DDS distribution.
For more information, type 'rtiddsgen -help' at a command shell
or consult the Code Generator User's Manual.
*/

import com.rti.dds.typecode.*;

public class  HostSimpleTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int __i=0;
        StructMember sm[]=new StructMember[6];
        Annotations memberAnnotation;

        memberAnnotation = new Annotations();
        memberAnnotation.default_annotation(AnnotationParameterValue.ZERO_USHORT);
        memberAnnotation.min_annotation(AnnotationParameterValue.MIN_USHORT);
        memberAnnotation.max_annotation(AnnotationParameterValue.MAX_USHORT);
        sm[__i] = new  StructMember("id", false, (short)-1, true, TypeCode.TC_USHORT, 0, false, memberAnnotation);__i++;
        memberAnnotation = new Annotations();
        memberAnnotation.default_annotation(AnnotationParameterValue.ZERO_USHORT);
        memberAnnotation.min_annotation(AnnotationParameterValue.MIN_USHORT);
        memberAnnotation.max_annotation(AnnotationParameterValue.MAX_USHORT);
        sm[__i] = new  StructMember("datacenterId", false, (short)-1,  false, TypeCode.TC_USHORT, 1, false, memberAnnotation);__i++;
        memberAnnotation = new Annotations();
        memberAnnotation.default_annotation(AnnotationParameterValue.ZERO_LONG);
        memberAnnotation.min_annotation(AnnotationParameterValue.MIN_LONG);
        memberAnnotation.max_annotation(AnnotationParameterValue.MAX_LONG);
        sm[__i] = new  StructMember("timestampOfReport", false, (short)-1,  false, TypeCode.TC_LONG, 2, false, memberAnnotation);__i++;
        memberAnnotation = new Annotations();
        memberAnnotation.default_annotation(AnnotationParameterValue.ZERO_ULONG);
        memberAnnotation.min_annotation(AnnotationParameterValue.MIN_ULONG);
        memberAnnotation.max_annotation(AnnotationParameterValue.MAX_ULONG);
        sm[__i] = new  StructMember("ram", false, (short)-1,  false, TypeCode.TC_ULONG, 3, false, memberAnnotation);__i++;
        memberAnnotation = new Annotations();
        memberAnnotation.default_annotation(AnnotationParameterValue.ZERO_ULONG);
        memberAnnotation.min_annotation(AnnotationParameterValue.MIN_ULONG);
        memberAnnotation.max_annotation(AnnotationParameterValue.MAX_ULONG);
        sm[__i] = new  StructMember("bw", false, (short)-1,  false, TypeCode.TC_ULONG, 4, false, memberAnnotation);__i++;
        memberAnnotation = new Annotations();
        memberAnnotation.default_annotation(AnnotationParameterValue.ZERO_ULONG);
        memberAnnotation.min_annotation(AnnotationParameterValue.MIN_ULONG);
        memberAnnotation.max_annotation(AnnotationParameterValue.MAX_ULONG);
        sm[__i] = new  StructMember("size", false, (short)-1,  false, TypeCode.TC_ULONG, 5, false, memberAnnotation);__i++;

        Annotations annotation = new Annotations();
        annotation.allowed_data_representation_mask(5);

        tc = TypeCodeFactory.TheTypeCodeFactory.create_struct_tc("ddsgen.HostSimple",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,  sm , annotation);
        return tc;
    }
}

