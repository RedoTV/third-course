<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
    <xs:element name="journal">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="journalInfo" type="journalType"/>
                <xs:element name="articles" type="articlesType"/>
            </xs:sequence>
        </xs:complexType>
    </xs:element>
    
    <xs:complexType name="journalType">
        <xs:all>
            <xs:element name="title" type="xs:string"/>
            <xs:element name="number" type="xs:positiveInteger"/>
            <xs:element name="dateYear" type="xs:integer"/>
        </xs:all>
        <xs:attribute name="lang" type="xs:string" use="optional" default="RUS"/>
    </xs:complexType>
    
    <xs:complexType name="articlesType">
        <xs:sequence>
            <xs:sequence maxOccurs="unbounded">
                <xs:element name="secTitle">
                    <xs:complexType>
                        <xs:simpleContent>
                            <xs:extension base="xs:string">
                                <xs:attribute name="lang" type="xs:string" use="optional" default="RUS"/>
                            </xs:extension>
                        </xs:simpleContent>
                    </xs:complexType>
                </xs:element>
                <xs:element name="article" type="articleType"/>
            </xs:sequence>
        </xs:sequence>
    </xs:complexType>
    
    <xs:complexType name="articleType">
        <xs:sequence>
            <xs:element name="authors" type="authorsType"/>
            <xs:element name="artTitles" type="artTitlesType"/>
            <xs:element name="abstracts" type="abstractsType"/>
            <xs:element name="codes" type="codesType"/>
            <xs:element name="keywords" type="keywordsType"/>
            <xs:element name="references" type="referencesType"/>
            <xs:element name="dateReceived" type="xs:string"/>
        </xs:sequence>
    </xs:complexType>
    
    <xs:complexType name="authorsType">
        <xs:sequence>
            <xs:element name="author" maxOccurs="2">
                <xs:complexType>
                    <xs:sequence>
                        <xs:element name="individInfo" maxOccurs="unbounded">
                            <xs:complexType>
                                <xs:sequence>
                                    <xs:element name="surname" type="xs:string"/>
                                    <xs:element name="initials" type="xs:string"/>
                                    <xs:element name="orgName" type="xs:string"/>
                                </xs:sequence>
                                <xs:attribute name="lang" use="required">
                                    <xs:simpleType>
                                        <xs:restriction base="xs:string">
                                            <xs:enumeration value="RUS"/>
                                            <xs:enumeration value="ENG"/>
                                        </xs:restriction>
                                    </xs:simpleType>
                                </xs:attribute>
                            </xs:complexType>
                        </xs:element>
                    </xs:sequence>
                    <xs:attribute name="num" type="xs:string" use="required"/>
                </xs:complexType>
            </xs:element>
        </xs:sequence>
    </xs:complexType>
    
    <xs:complexType name="artTitlesType">
        <xs:sequence>
            <xs:element name="artTitle" maxOccurs="unbounded">
                <xs:complexType>
                    <xs:simpleContent>
                        <xs:extension base="xs:string">
                            <xs:attribute name="lang" type="xs:string" use="required"/>
                        </xs:extension>
                    </xs:simpleContent>
                </xs:complexType>
            </xs:element>
        </xs:sequence>
    </xs:complexType>
    
    <xs:complexType name="abstractsType">
        <xs:sequence>
            <xs:element name="abstract" maxOccurs="unbounded">
                <xs:complexType>
                    <xs:simpleContent>
                        <xs:extension base="xs:string">
                            <xs:attribute name="lang" type="xs:string" use="required"/>
                        </xs:extension>
                    </xs:simpleContent>
                </xs:complexType>
            </xs:element>
        </xs:sequence>
    </xs:complexType>
    
    <xs:complexType name="codesType">
        <xs:sequence>
            <xs:element name="udk">
                <xs:simpleType>
                    <xs:restriction base="xs:string">
                        <xs:minLength value="1"/>
                        <xs:maxLength value="69"/>
                    </xs:restriction>
                </xs:simpleType>
            </xs:element>
            <xs:element name="edn" type="xs:string"/>
        </xs:sequence>
    </xs:complexType>
    
    <xs:complexType name="keywordsType">
        <xs:sequence>
            <xs:element name="kwdGroup" maxOccurs="unbounded">
                <xs:complexType>
                    <xs:sequence>
                        <xs:element name="keyword" type="xs:string" maxOccurs="unbounded"/>
                    </xs:sequence>
                    <xs:attribute name="lang" type="xs:string" use="required"/>
                </xs:complexType>
            </xs:element>
        </xs:sequence>
    </xs:complexType>
    
    <xs:complexType name="referencesType">
        <xs:sequence>
            <xs:element name="reference" maxOccurs="unbounded">
            </xs:element>
        </xs:sequence>
    </xs:complexType>
</xs:schema>