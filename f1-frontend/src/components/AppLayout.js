import { Flex, Layout } from 'antd';
import { useNavigate } from 'react-router-dom';

const { Header, Content, Footer } = Layout;

const AppLayout = (props) => {
    const navigate = useNavigate();

    const gotoHome = () => {
        navigate('/');
    }

    return (
        <div className='app-layout-container'>
            <Layout className='app-layout'>
                <Header className='app-layout-header'>
                    <Flex align='center' justify='space-between'>
                        <div className='app-title' onClick={gotoHome}>
                            <img src='/img/f1_logo.png' alt='f1-logo' /> Data
                        </div>
                    </Flex>
                </Header>
                <Content>
                <div className='app-layout-content-container'>
                    { props.children }
                </div>
                </Content>
                <Footer className='app-layout-footer'>
                    Bryan Ogden ©{new Date().getFullYear()}
                </Footer>
            </Layout>
        </div>
    );
}

export default AppLayout
