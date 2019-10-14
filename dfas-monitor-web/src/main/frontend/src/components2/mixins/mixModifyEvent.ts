export default (...events) => {
    return {
        created() {
            for (let e of events) {
                if (this.$listeners[e]) {
                    this.$listeners[e] = () => void 0;
                }
            }
        }
    };
};
