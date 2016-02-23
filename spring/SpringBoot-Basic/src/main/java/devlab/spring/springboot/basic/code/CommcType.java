package devlab.spring.springboot.basic.code;

/**
 * Created by junemp on 2016. 2. 21..
 */
public enum CommcType {
        SKT("SKT"),
        KT("KT");

        private String commcType;

        private CommcType(String commcType) {
            this.commcType = commcType;
        }

        public String code() {
            return commcType;
        }
}
