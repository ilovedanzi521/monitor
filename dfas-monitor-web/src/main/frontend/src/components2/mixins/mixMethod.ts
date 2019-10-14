export default (Component, ref) => {
    return {
        mounted() {
            let keys = Object.keys(Component.methods || {});
            for (let key of keys) {
                this[key] = this.$refs[ref][key];
            }
        }
    };
};
