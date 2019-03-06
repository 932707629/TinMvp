<?xml version="1.0"?>
<#import "root://activities/common/kotlin_macros.ftl" as kt>
<recipe>
  <@kt.addAllKotlinDependencies />
<#if needActivity>
    <merge from="root/AndroidManifest.xml.ftl"
           to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />
</#if>

<#if needActivity && generateActivityLayout>
    <instantiate from="root/res/layout/simple.xml.ftl"
                 to="${escapeXmlAttribute(resOut)}/layout/${activityLayoutName}.xml" />
	<open file="${escapeXmlAttribute(resOut)}/layout/${activityLayoutName}.xml" />	 
</#if>

<#if needActivity>
    <instantiate from="root/src/app_package/TinActivity.${ktOrJavaExt}.ftl"
                   to="${projectOut}/src/main/java/${slashedPackageName(ativityPackageName)}/${pageName}Activity.${ktOrJavaExt}" />
    <open file="${projectOut}/src/main/java/${slashedPackageName(ativityPackageName)}/${pageName}Activity.${ktOrJavaExt}" />
</#if>

<#if needView>
    <instantiate from="root/src/app_package/TinView.${ktOrJavaExt}.ftl"
                   to="${projectOut}/src/main/java/${slashedPackageName(viewPackageName)}/${pageName}View.${ktOrJavaExt}" />
	<open file="${projectOut}/src/main/java/${slashedPackageName(viewPackageName)}/${pageName}View.${ktOrJavaExt}" />	 			   
</#if>

<#if needPresenter>
    <instantiate from="root/src/app_package/TinPresenter.${ktOrJavaExt}.ftl"
                   to="${projectOut}/src/main/java/${slashedPackageName(presenterPackageName)}/${pageName}Presenter.${ktOrJavaExt}" />
    <open file="${projectOut}/src/main/java/${slashedPackageName(presenterPackageName)}/${pageName}Presenter.${ktOrJavaExt}" />
</#if>

</recipe>
