package com.transformers.demo.model.transformer;
import com.ibm.icu.text.Transliterator;

public class Transformer3 extends TransformerImpl {
    public Transformer3(String transformerId, String[] parameters) {
        this.setTransformerId(transformerId);
        this.setParameters(parameters);
    }

    @Override
    public String applyTransformer(String value){
        Transliterator cyrillicGrecTransliterator = Transliterator.getInstance(
            "Greek-Latin/UNGEGN; Cyrillic-Latin; nfd; [:Nonspacing Mark:] remove; nfc");
        return cyrillicGrecTransliterator.transliterate(value);
    }
}
