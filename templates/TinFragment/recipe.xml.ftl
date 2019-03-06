<?xml version="1.0"?>
<#import "root://activities/common/kotlin_macros.ftl" as kt>
<recipe>
  <@kt.addAllKotlinDependencies />
  
<#if needFragment && generateFragmentLayout>
    <instantiate from="root/res/layout/simple.xml.ftl"
                 to="${escapeXmlAttribute(resOut)}/layout/${fragmentLayoutName}.xml" />
	<open file="${escapeXmlAttribute(resOut)}/layout/${fragmentLayoutName}.xml" />
</#if>

<#if needFragment>
    <instantiate from="root/src/app_package/TinFragment.${ktOrJavaExt}.ftl"
                   to="${projectOut}/src/main/java/${slashedPackageName(fragmentPackageName)}/${pageName}Fragment.${ktOrJavaExt}" />
    <open file="${projectOut}/src/main/java/${slashedPackageName(fragmentPackageName)}/${pageName}Fragment.${ktOrJavaExt}" />
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
