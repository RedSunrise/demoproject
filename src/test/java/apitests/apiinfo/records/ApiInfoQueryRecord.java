package apitests.apiinfo.records;

public record ApiInfoQueryRecord(
         int pageid
        ,int ns
        ,String title
        ,String contentmodel
        ,String pagelanguage
        ,String pagelanguagehtmlcode
        ,String pagelanguagedir
        ,String touched
        ,int lastrevid
        ,int length
        ,String fullurl
        ,String editurl
        ,String canonicalurl
){}
